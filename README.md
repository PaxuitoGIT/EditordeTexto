# Caso Final Integrador 4

Link del repositorio: https://github.com/PaxuitoGIT/EditordeTexto

## Explicación del programa

### Ejercicio 1 y 2

Para esta parte, he implementado en FuncionesArchivo las funciones de abrir el archivo, la función del guardado, tanto guardar (sobreescribir) y guardar como (extensión). 
Además, cuando se intenta abrir un archivo, aparece una ventana para seleccionar el archivo que se desee.

De base se debería de abrir justo en el directorio del proyecto para que no se guarde en sitios no correspondidos.

### Ejercicio 3 y 4

A continuación, en FuncionesVer están las funciones de comparar texto donde se seleccionan dos archivos y en la interfaz aparece si son iguales o son diferentes. 
A su vez, está también la función de analizar texto, donde se selecciona un archivo y aparece el uso frecuente de las palabras del texto.

### Ejercicio 5 y 6

Por otra parte, en FuncionesVer está la búsqueda de palabras en el que si introduces la palabra en el texto seleccionado, te cuenta cuántas veces ha aparecido. 

Luego, en FuncionesContacto está la posibilidad de agregar contactos en el que aparecerá una ventanita para introducir los datos. Una vez pinchado el guardar, se creará un .txt en el directorio del proyecto
llamada contactos y cualquier contacto más se guardará ahí. Para ver los contactos, basta con darle a abrir o mostrar contactos para seleccionar el contactos.txt y mostrar en la pantalla.

### Ejercicio 7, 8 y 9

Lamentablemente, no soy capaz de implementar la multiplicidad de ventanas en el programa a la vez que el seguimiento del ratón. Hay un commit en el que pude hacerlo llamada 
"Múltiples Ventanas Provisional" y funcionaba con el JTabbedPane para crear varias pestañas dentro de la ventana pero lo tuve que revertir debido al seguimiento del ratón que mostraba un error que no fui capaz
de solucionar a pesar de intentarlo muchas veces.

En FuncionesMisceláneas está escrita el seguimiento de las coordenadas X e Y del cursor y la barra de desplazamiento está añadida como AS_NEEDED, para cuando se sobrepase de la ventana. 

### Ejercicio 10 y 11

En FuncionesContacto agregué un indicador visual en la parte de agregar el correo en los contactos siendo rojo que está incorrecta y verde que está correcta cuando se cumple que una dirección estándar son
"caracteres" + "@" + "caracteres" + "." + "caracteres".

Por último, en FuncionesDibujo está la parte donde se crea una ventana nueva para dibujar en negro.


### Cosas extras

Añadí varias cosas extras como el poder cambiar la fuente de letra, su tamaño y un salto de línea cuando el texto que vas a escribir se sobrepasa del texto. Por defecto, está activado. 

Además, en FuncionesEditar añadí la opción de rehacer y deshacer la última acción cometida en el texto y en FuncionesMisceláneas añadí la creación de atajos por teclado siendo algunos ejemplos como 
"ctrl + z" para deshacer o "F2" para abrir archivos. Está todo documentado también en el código.

## Muchas gracias por su lectura
