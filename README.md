# RESET_Automacao_API

REPORT:

(POST) BUG01 – API aceita cadastro com payload contendo informações extras.

Reservas estão sendo aceitas com parametros a mais no body.
Método utilizado para o teste: PostBookingTest.validarCadastroDeNovaReservaComInfoExtra()
Status code esperado: 400.
Status code recebido: 200.
Body utilizado para o teste:

{
"firstname" : "Jim",
"lastname" : "Brown",
"totalprice" : 111,
"depositpaid" : true,
"bookingdates" : {
"checkin" : "1111-01-01",
"checkout" : "2222-01-01"
},
"additionalneeds" : "Breakfast",
"peso" : 80,
"altura" : 1.75,
"signo" : "gemini",
"pet" : "dog"
}

Severidade: CRÍTICA Prioridade: 2 - Média.

(POST) BUG02 – API aceita cadastro de reservas com datas no passado.

Reservas estão sendo aceitas com datas anteriores ao dia atual.
Método utilizado para o teste: PostBookingTest.recusarCadastroDeNovaReservaComDataAnterior()
Status code esperado: 422.
Status code recebido: 200.
Body utilizado para o teste:

{
"firstname" : "Jim",
"lastname" : "Brown",
"totalprice" : 111,
"depositpaid" : true,
"bookingdates" : {
"checkin" : "1996-01-01",
"checkout" : "1998-01-01"
},
"additionalneeds" : "Breakfast"
}

Severidade: CRÍTICA Prioridade: 1 - Alta.

(POST) BUG03 – API aceita cadastro de reservas sem validação de login.

Reservas estão sendo aceitas sem qualquer "Authorization", esta informação não é sequer solicitada no header na documentação.
Método utilizado para o teste: PostBookingTest.barrarCadastroDeNovaReservaSemAutenticacao()
Status code esperado: 401.
Status code recebido: 200.
Body utilizado para o teste:

{
"firstname" : "Jim",
"lastname" : "Brown",
"totalprice" : 111,
"depositpaid" : true,
"bookingdates" : {
"checkin" : "2001-01-01",
"checkout" : "2002-01-01"
},
"additionalneeds" : "Breakfast"
}

Severidade: CRÍTICA Prioridade: 1 - Alta.

(GET) BUG04 – Filtro checkin está quebrando as buscas.

Ao buscar uma reserva por sua data exata de checkin a api não retorna em qualquer pesquisa que o filtro "checkin" está incluso.
Métodos utilizados para os testes:
GetBookingTest.validarListagemDeIdsComFiltroCheckin()
GetBookingTest.validarListagemDeIdsComFiltroCheckinECheckout()
GetBookingTest.validarListagemDeIdsComFiltroNomeECheckinECheckout()

id = valor de id recebido ao criar nova reserva dentro do método com postBookingRequest.getIdbookingCreate()
Informação esperada e não recebida no body: "bookingid", (hasItem(id)).

Obs.: Acredito que o filtro possua uma formatação que faça a pesquisa por datas maiores que a data informada.
O correto seria o filtro buscar datas maiores e iguais a data informada.

Severidade: CRÍTICA Prioridade: 1 - Alta.

Melhorias:
1- Documentação solicita retorno 201 para os métodos (booking)DELETE e (ping)GET. Acredito ser mais correto o status 200.
2- Documentação solicita retorno 200 para os métodos (booking)POST e (auth)POST. Acredito ser mais correto o status 201.
3- Documentação está solicitando que o .header() de validação dos métodos PUT, PATCH e DELETE do booking
seja colocado "Authorisation" enquanto o correto é "Authorization". Acredito que seja um erro de digitação na documentação
pois ao usar o Postman o .header() é automaticamente gerado com o termo correto.
4-API gera retorno 405 para os métodos (booking)PUT e (booking)DELETE ao informar uma reserva inexistente, acredito ser mais correto 404.
5-API gera retorno 500 para o método (booking)GET ao informar um filtro mal formatado, acredito ser mais correto 400.
6-API gera retorno 500 para o método (booking)POST ao informar um payload inválido ao body, acredito ser mais correto 400.





