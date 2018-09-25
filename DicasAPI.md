# Dicas para utilização da API do World of Warcraft
*Observação*: substitua o XXXXX no final de cada chamada pela chave de acesso.

## Achievements (Conquistas)

Para receber as informações dos últimos Achievements de uma guilda, utilize:
* GET https://us.api.battle.net/wow/guild/Goldrinn/Juppongatana?fields=achievements&locale=pt_BR&apikey=XXXXX

A chamada acima retornará basicamente IDs dos Achievements e a data em que a guilda obteve cada Achievement. Desta forma, faz-se necessário realizar uma ou mais requisições adicionais para pegar mais informações dos Achievements conforme necessário (nem todos os Achievements irão para o feed).

Com o id de um dos Achievements é possível pegar seus dados usando:
* GET https://us.api.battle.net/wow/achievement/4860?locale=pt_BR&apikey=XXXXX

Esta chamada retornará todas as informações do Achievement em questão, como o nome, descrição e id do ícone.

Para pegar a imagem do ícone, utilize o id do ícone (obtido na chamada acima) na url abaixo:
* http://media.blizzard.com/wow/icons/36/ID_DO_ICONE.jpg

O número 36 presente no link acima determina o tamanho do ícone. Existem três possibilidades de tamanho: 18, 36, e 56. [Clique aqui para ver um exemplo de ícone.](http://media.blizzard.com/wow/icons/56/inv_guild_standard_horde_a.jpg)
