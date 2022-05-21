# RESET_Automacao_API
teste

Delete http: Documentação solicita retorno 201 (created) api retorna 200 (ok). Como 201 para Delete 
faz menos sentido que 200, deixei o teste validando o retorno 200. Acredito que a documentação
esteja com erro.

Bug(POST): Cadastro com payload contendo info extra é aceito.
Bug(POST): Cadastro de reserva com datas no passado é aceito.
Bug(POST): Criação de reserva não solicita token.
Bug(PUT): Alteração de reserva inexistente gera 403 forbiden e não 404 notfound.
Bug(GET): Busca com filtro checkin está retornando apenas datas posteriores, ignorando o próprio dia informado no filtro(3).



