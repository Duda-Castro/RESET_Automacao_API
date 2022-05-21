# RESET_Automacao_API
teste

Delete http: Documentação solicita retorno 201 (created) api retorna 200 (ok). Como 201 para Delete 
faz menos sentido que 200, deixei o teste validando o retorno 200. Acredito que a documentação
esteja com erro.

Bug(POST): Cadastro com payload contendo info extra é aceito.
Bug(PATCH): Atualização parcial com Patch retorna 403 mesmo com token válido ou Basic.
Bug(PUT): Método put não está permitindo alterações mesmo com autorização.
Bug(PUT): Alteração de reserva inexistente gera 403 forbiden e não 404 notfound.

TAREFAS PENDENTES:


