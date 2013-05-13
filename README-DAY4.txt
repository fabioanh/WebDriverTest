Que beneficios cree aportan estos Design Patterns en el desarrollo de tests?
Como todos los patrones de diseño aportan grandes ventajas al momento de modificaciones futuras, de manera que se unifican los lugares en los cuales se utilizan ciertos objetos de páginas recurrentes y se genera una mejor organización en este caso modelando las páginas de la aplicación como objetos en java.

Que problemas podemos tener en nuestros tests si no definimos una buena arquitectura/diseño
Se pueden tener problemas para el mantenimiento de las pruebas haciendo el trabajo de automatización más difícil de lo que realmente debería ser.

Que problemas encuentra en nuestros tests actualmente? Que debidades podrian tener? 
En el diseño de la solución que di pueden existir problemas pues trabajé con una clase padre abstracta. Teniendo esto la funcionalidad de PageFactory no se dio de la manera esperada debido a la herencia de las clases. Además de esto también se tienen una gran cantidad de referencias XPath que seguramente no son las más adecuadas.
