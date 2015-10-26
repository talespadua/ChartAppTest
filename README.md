##ChartApp

Esse app é um exemplo de funcionalidade da Lib MPAndroidChart, utilizando
gráfico de barras (BarChart)

Os dados do gráfico são consumidos através de API REST escrita em Python 2.7,
que roda em localhost:8084  

Execute o script com o comando "python backend.py"  

O webservice tem como dependência o micro-framework Bottle.  
Bottle pode ser instalado com o pip: pip install bottle

Em Windows o pip se encontra em C:Python27/Scripts  
Em Mac, pip pode ser instalado com: easy_install pip  
Em linux (Ubuntu, Debian e derivados): apt-get install python-pip python-dev  

Para mudar os valores de entrada, fazer requisição POST em /update_balance com
valores val1, val2... val5
