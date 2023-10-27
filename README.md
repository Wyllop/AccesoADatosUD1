# Aplicación de Biblioteca-Acceso Base de Datos
La Aplicación de Biblioteca es un programa Java que te permite gestionar una biblioteca, realizar búsquedas de libros, agregar nuevos libros, registrar préstamos y devoluciones de libros. A continuación, se proporciona una descripción general de la aplicación y cómo usarla.

##  Requisitos Previos
Antes de ejecutar la Aplicación de Biblioteca, asegúrate de que Java esté instalado en tu sistema y configurado correctamente.

##  Funcionalidades Principales

1.Interfaz de Usuario
La aplicación utiliza una interfaz de usuario basada en Java Swing para proporcionar una experiencia fácil de usar. Cuando ejecutes la aplicación, se abrirá una ventana con las siguientes secciones:

Lista de Libros: En el lado izquierdo de la ventana, verás una lista de libros disponibles en la biblioteca. Puedes buscar libros según su título, autor e ISBN, y también puedes agregar nuevos libros.

Registro de Préstamos: En el lado derecho de la ventana, se muestra un registro de préstamos de libros, donde puedes registrar préstamos y devoluciones.

2.Búsqueda de Libros
Puedes buscar libros en la biblioteca proporcionando detalles como el título, autor o ISBN en los campos de búsqueda correspondientes. Presiona el botón "Buscar" para buscar libros que coincidan con los criterios de búsqueda.

3.Agregar Libros
Puedes agregar nuevos libros a la biblioteca ingresando los detalles del libro, como título, autor e ISBN, en los campos correspondientes en la sección izquierda. Luego, presiona el botón "Agregar Libro" para agregar el libro a la lista de libros disponibles.

4.Registro de Préstamos
Puedes registrar préstamos de libros proporcionando el nombre del usuario que solicita el préstamo y seleccionando un libro de la lista de libros disponibles. Después de registrar un préstamo, el libro aparecerá en el registro de préstamos.

5.Registro de Devoluciones
Para registrar la devolución de un libro, selecciona un préstamo en la lista de registros de préstamos en el lado derecho de la ventana y presiona el botón "Registrar Devolución". El libro devuelto se eliminará de la lista de registros de préstamos.

##  Almacenamiento de Datos
La aplicación almacena los datos de libros y registros de préstamos en archivos de texto en las siguientes ubicaciones:

Archivo de Libros: libros.txt
Archivo de Registros de Préstamos: prestamos.txt
Estos archivos se utilizan para cargar datos existentes al iniciar la aplicación y guardar nuevos datos cuando agregas libros o registros de préstamos.

Uso de Archivos de Datos
Si deseas manipular directamente los archivos de datos, ten en cuenta el formato en el que se almacenan los datos en los archivos mencionados anteriormente.

##  Notas Importantes
Asegúrate de mantener los archivos de datos en las ubicaciones especificadas (libros.txt y prestamos.txt).
La aplicación no realiza una validación exhaustiva de los datos ingresados, por lo que debes introducir información válida y precisa.
