# RESET_Automacao_API
teste

Bug: Pesquisa pelo "filtro data" não retorna se o filtro data for o mesmo valor da data cadastrada. 
Delete http: Documentação solicita retorno 201 (created) api retorna 200 (ok). Como 201 para Delete 
faz menos sentido que 200, deixei o teste validando o retorno 200. Acredito que a documentação
esteja com erro.
Bug: Alteração utilizando Authorisation no método put não funciona.
Bug: Alteração sem token funciona.
TAREFAS PENDENTES:

🧐Suite AcceptanceExceptionTest:

/GET
Fazer consulta de reservas enviando filtro mal formatado
/POST
Fazer criação de reserva com payload inválido
Validar a criação de mais de uma reserva em sequência
Criar uma reserva enviando mais parâmetros no payload
/PUT
Alterar uma reserva que não existe
