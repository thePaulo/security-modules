# Seguran�a Backend PagRN

## Contribuidores

Nome                     | RA    
-------------------------| ------
PV |20222001137 
Pedro             |20222000120  

O pagrn utiliza o sistema do keycloak para autentica��o e autoriza��o no sistema, o keycloak gerencia usu�rios, pap�is e permiss�es em um banco postgres separado do banco do pagrn.

Este documento teve como base no keycloak 15.0.02

# Configura��o do ambiente

� necess�rio a instala��o do keycloak por meio do site 
https://www.keycloak.org/downloads , ap�s a instala��o, o dev dever� ir at� a pasta BIN do keycloak e rodar o `standalone.bat` ou `standalone.sh` dependendo do OS e por padr�o ele tentar� rodar na porta 8080.

Por padr�o o keycloak roda em um banco h2, para alterar para o postgres � necess�rio algumas etapas extras.

o jar do postgres pode ser instalado por meio do site
https://jdbc.postgresql.org/download/

Crie ou altere caso j� exista os arquivos na seguinte pasta
\modules\system\layers\keycloak\org\postgresql\main

Dever� ter 2 arquivos:
module.xml
{postgresql-SUA-VERSAO}.jar

Com o .jar do postgres colocado nesta pasta, altere o `module.xml` para ter os seguintes valores

```
<?xml version="1.0" ?>
<module xmlns="urn:jboss:module:1.3" name="org.postgresql">

    <resources>
        <resource-root path="postgresql-{SUA-VERSAO}.jar"/>
    </resources>

    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>
```
Ap�s isso, na pasta \standalone\configuration altere o arquivo `standalone.xml`

<b>SUBSTITUA</b> o datasource KeycloakDS pelo seguinte

```
<datasource jndi-name="java:jboss/datasources/KeycloakDS"           pool-name="KeycloakDS" enabled="true" use-java-context="true">
    <connection-url>jdbc:postgresql://localhost/keycloak</connection-url>
    <driver>postgresql</driver>
    <pool>
        <max-pool-size>20</max-pool-size>
    </pool>
    <security>
        <user-name>{NOME DO SEU USER DO BANCO}</user-name>
        <password>{NOME DA SUA SENHA NO BANCO}</password>
    </security>
</datasource>
```

e adicione abaixo nos drivers a seguinte parte

```
<driver name="postgresql" module="org.postgresql"> 
    <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
</driver> 
```

Desta forma o keycloak rodar� em um banco postgres

#### <b><i>obs: esta configura��o s� � v�lida para o keycloak rodando local, se for por meio de um container docker o procedimento de configura��o � diferente</i></b>


# Como funciona o keycloak?


![image](wiki/keycloak_home.png "keycloak home")

No canto superior esquerdo � poss�vel ver "Pagrn" escrito abaixo do logo do keycloak, este realm foi criado manualmente, para evitar o uso do default do keycloak que � o "master".

![image](wiki/keycloak_clients.png "keycloak clients")

Acessando a parte de clients, temos 2 clientes relevantes, o `auth-pagrn` que foi criado por n�s, e o `admin-cli`, o auth-pagrn n�s utilizamos para requisi��es autenticadas sobre os usu�rios/sistema, enquanto o admin-cli � utilizado para a��es que envolvam altera��o dos usu�rios do keycloak

![image](wiki/keycloak_client_settings.png "keycloak client settings")

Ao acessar nosso cliente do keycloak ( auth-pagrn ) � necess�rio configurar estas informa��es, na parte de web origins fica � disposi��o de quais origens ele vai permitir para n�o causar problemas de CORS

#### <b><i>obs: no cliente do admin-cli � necess�rio marcar o Service Accounts Enable </b></i>

![image](wiki/keycloak_credentials.png "keycloak client credentials")

Na parte de credenciais do cliente, h� a chave secreta que � necess�ria para fazer a requisi��o inicial com informa��es do usu�rio para pegar o token de acesso, fica o adendo que existem 1 chave para cada client, portanto a chave do admin-cli � diferente da chave do cliente auth-pagrn

![image](wiki/keycloak_roles.png "keycloak roles")

Na parte de roles � poss�vel ver as roles que ir�o ser consumidas pelo pagrn, a ideia proposta � que haver�o roles para cada evento ou funcionalidade que requerir permiss�es especiais, fazendo assim o controle de autoriza��o

![image](wiki/keycloak_users.png "keycloak users")

Na listagem de usu�rios � poss�vel ver os usu�rios listados no banco de dados do keycloak, � importante que o username de cada usu�rio seja respectivo ao cpf do usu�rio cadastrado no backend do pagrn para permitir a troca de informa��o entre o frontend e o backend

![image](wiki/keycloak_user_roles.png "keycloak user roles")

Em role mappings � poss�vel decidir quais as roles que v�o ser associadas ao usu�rio

# Login

![image](wiki/keycloak_login.png "keycloak login")

O login no keycloak deve ser feito por meio de um POST para a URL:
http://localhost:8080/auth/realms/pagrn/protocol/openid-connect/token 


usando o body com x-www-form-urlencoded
```shell
{
    "username":<cpf do usuario logando>,
    "password":"<senha do usuario>",
    "grant_type":"password",
    "client_id":"<auth-pagrn OU admin-cli>",
    "client_secret":"<client secret do keycloak>"
}
```
#### <b><i>obs: login usando o admin-cli s� necessita grant_type, client_id e client_secret </i></b>
<br>
<b>O login tamb�m pode ser diretamente feito pro nosso servidor no endpoint auth/login passando apenas username e senha, de forma mais simplificada e ignorando algumas informa��es que n�o s�o utilizadas.</b>

<br>
Ap�s feito esse processo (diretamente pelo keycloak), ser� devolvido um json com algumas informa��es

```shell
{
    "access_token",
    "expires_in",
    "refresh_expires_in",
    "refresh_token",
    "token_type",
    "not-before-policy",
    "session_state",
    "scope"
}
```
* o access_token � a informa��o principal que precisamos da resposta do login, atrav�s dele iremos ter permiss�o para fazermos requisi��es ao pagrn
* expires_in � o tempo no qual a sess�o do token de acesso do usu�rio expirar�
* refresh_expires_in � o tempo no qual o refresh token expirar�
* refresh_token � utilizado para pegar um novo token de acesso ap�s um ter expirado
* token_type � "Bearer"
* Outras informa��es s�o irrelevantes para o sistema


# Requests autenticados

![image](wiki/authenticated_request.png "authenticated request")

Ap�s de ter sido feito um login bem sucedido os requests para endpoints do pagrn dever�o conter no cabe�alho a key: Authorization com o value: `Bearer    <SEU TOKEN DE AUTENTICA��O>`

## Outros requests importantes -- admin-cli

#### Todos usu�rios no keycloak:
GET: 
http://localhost:8080/auth/admin/realms/pagrn/users/

#### vers�o pelo pagrn
`TODO !!!!`

#### Reset de senha de um user keycloak:
PUT:
http://localhost:8080/auth/admin/realms/pagrn/users/ID-USUARIO-NO-KEYCLOAK/reset-password

body:
```
{
    "type":"password",
    "value":"<sua nova senha>",
    "temporary":false
}
```
#### vers�o pelo pagrn
/reset/{username}


#### Esqueci minha senha keycloak:
PUT:
http://localhost:8080/auth/admin/realms/pagrn/users/ID-USUARIO-NO-KEYCLOAK/execute-actions-email

body:
```
["UPDATE_PASSWORD"]
```

#### vers�o pelo pagrn
`TODO !!!!`

#### <i><b>obs: para rodar a funcionalidade de esqueci minha senha � necess�rio configurar o email em realm management</i></b>

C'est �a




### [Home](home)

<ul> 
    <li>Seguran�a
        <ul>
            <li>Configura��o do keycloak
                <ul>
                    <li>[Configua��o via arquivo](keycloak_file_config)</li>
                    <li>Configura��o via docker</li>
                </ul>
            </li>
            <li>[Utiliza��o do keycloak](keycloak_usage)</li>
            <li>[Requisi��es](requests)</li>
            <li>Aspectos</li>
        </ul>
    </li>
</ul>

<hr>

- [Sidebar](_sidebar)