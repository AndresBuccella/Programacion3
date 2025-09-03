package elResto;

public class Hashing {
    /*
     * Trabajo práctico 6 - Estructuras de Dispersión
Programación 3 - TUDAI
Ejercicio 1.
Dado el conjunto de elementos: X= {68, 42, 47, 5, 76, 95, 23, 88, 90, 85, 31, 71, 60, 10, 46, 61, 50, 92, 74, 6, 97, 66, 1, 56, 27, 7, 14, 92}
Realizar la inserción de los mismos en una estructura de Hashing com la que se especifica
en cada punto, con las siguientes técnicas de tratamiento de desbordes, muestre
gráficamente cómo se va armando la estructura y cómo queda luego de insertar hasta el
último elemento:
a.- Hashing separado (con M=7 y rp=1, rs=1).
b.- Hashing separado con crecimiento (con el comportamiento de HashTable de JAVA) (con
M=7, ρd=0,9)


X= {68, 42, 47, 5, 76, 95, 23, 88, 90, 85, 31, 71, 60, 10, 46, 61, 50, 92, 74, 6, 97, 66, 1, 56, 27, 7, 14, 92}
a) 68%7*1*1 = 5
|_|_|_|_|_|68|_|
42%7
|42|_|_|_|_|68|_|
47%7 = 5
            47
|42|_|_|_|_|68|_|
5%7=0
             5
            47
|42|_|_|_|_|68|_|
76%7=6
             5
            47
|42|_|_|_|_|68|76|
95%7=
              5
             47
|42|_|_|_|95|68|76|
23%7
               5
              47
|42|_|23|_|95|68|76|

Asi sucesivamente...

b)
X= {68, 42, 47, 5, 76, 95, 23, 88, 90, 85, 31, 71, 60, 10, 46, 61, 50, 92, 74, 6, 97, 66, 1, 56, 27, 7, 14, 92}
M=7, ρd=0,9  L=(M*0,9*rp)función piso = 6 = L
68%7*1*1 = 5
|_|_|_|_|_|68|_|
42%7
|42|_|_|_|_|68|_|
47%7 = 5
            47
|42|_|_|_|_|68|_|
5%7=0
             5
            47
|42|_|_|_|_|68|_|
76%7=6
             5
            47
|42|_|_|_|_|68|76|
95%7=
              5
             47
|42|_|_|_|95|68|76|
Como llegó al 90% de su capacidad, duplicamos la capacidad del arreglo y volvemos a calcular todo

X= {68, 42, 47, 5, 76, 95, 23, 88, 90, 85, 31, 71, 60, 10, 46, 61, 50, 92, 74, 6, 97, 66, 1, 56, 27, 7, 14, 92}
M = 7*2 = 14, ρd=0,9  L=(M*0,9*rp)función piso = 12 = L
68%14 = 12
|_|_|_|_|_|_|_|_|_|_|_|68|_|_|
42%12 = 6
|_|_|_|_|_|_|42|_|_|_|_|68|_|_|
47%12 = 11
|_|_|_|_|_|_|42|_|_|_|47|68|_|_|
5%12 = 5
|_|_|_|_|_|5|42|_|_|_|47|68|_|_|
76%12 = 4
|_|_|_|_|76|5|42|_|_|_|47|68|_|_|
95%12 = 11
                       95
|_|_|_|_|76|5|42|_|_|_|47|68|_|_|
23%12 = 11
                       23
                       95
|_|_|_|_|76|5|42|_|_|_|47|68|_|_|
88%12 = 4
                       23
         88            95
|_|_|_|_|76|5|42|_|_|_|47|68|_|_|
90%12 = 6
                                            23
                  88       90               95
| _ | _ | _ | _ | 76 | 5 | 42 | _ | _ | _ | 47 | 68 | _ | _ |
  0   1   2   3    4   5    6   7   8   9   10   11   12  13
85%12 = 1
                                                    23
                      88        90                  95
| __ | 85 | __ | __ | 76 | 05 | 42 | __ | __ | __ | 47 | 68 | __ | __ |
   0    1    2    3    4    5    6    7    8    9   10   11   12   13
31%12 = 7
                                                    23
                      88        90                  95
| __ | 85 | __ | __ | 76 | 05 | 42 | 31 | __ | __ | 47 | 68 | __ | __ |
   0    1    2    3    4    5    6    7    8    9   10   11   12   13

   
71%12 = 11
                                                    23
                      88        90                  95   71
| __ | 85 | __ | __ | 76 | 05 | 42 | 31 | __ | __ | 47 | 68 | __ | __ |
   0    1    2    3    4    5    6    7    8    9   10   11   12   13

X= {68, 42, 47, 5, 76, 95, 23, 88, 90, 85, 31, 71, 60, 10, 46, 61, 50, 92, 74, 6, 97, 66, 1, 56, 27, 7, 14, 92}
M = 14*2 = 28, ρd=0,9  L=(M*0,9*rp)función piso = 25 = L
Se hace este y se tiene que recalcular otra vez quedando en M = 56 þd = 0,9 L = 50


Ejercicio 2.
Conteste si es posible o no y justifique:
1. Si se quisiera listar en orden todas las claves almacenadas en una estructura de
hashing, ¿se podría? ¿Cómo? ¿Es la estructura más adecuada?
2. ¿Qué tipos de servicios resuelve un hashing? Es posible responder por ejemplo: La
lista de todos los alumnos que obtuvieron una nota mayor que x en un curso dado?

1. No es la estructura más adecuada pero si, se la puede recorrer con un iterador.
Iterator<Tipo> it = hashset.iterator();
while (it.hasNext()){
     Tipo elem = it.next(); //Acá estaria el elemento en cuestión para trabajarlo
}
2. Hashing resuelve consultas de igualdad, eliminación y adición O(1).
Si, es posible pero no se diferenciaria de una lista ya que de la forma que al menos me lo imagino, siendo un hashset por ejemplo
de tipo Alumno donde cada uno tiene su nota, tendria que recorrerlo con iterator uno por uno como si fuese una lista

Ejercicio 3.
Se está desarrollando una aplicación que almacena los datos y el saldo de las tarjetas de
compra de comida del comedor de una universidad. Cada cliente es identificado por su
número de DNI, y se poseen además sus datos personales y de la carrera que estudia.
CLIENTE: DNI, Nombre, Apellido, fecha de nacimiento, domicilio, CP ciudad de origen,
saldo de la cuenta, nombre carrera estudia
Se quiere:
a) Dado un DNI de cliente, responder el saldo de su cuenta.
b) Imprimir un listado de Nombre y Apellido de todos los clientes que tienen en su saldo de
cuenta menos de un valor X dado.
c) Dado un código postal, listar todos los clientes que provengan de esa ciudad.

Proponga y describa qué estructuras de datos utilizaría para responder eficientemente a los servicios pedidos.
Muestre gráficamente cómo se relacionan.

a)Elegiría un HashSet donde el hash se calcule por el dni, ya que la consulta seria O(1).
Tambien podria ser un array fijo de un número extremadamente grande como 100millones y la consulta igual seria O(1) pero tendria mucho espacio vacío.
Una lista en cambio recorreria todos los elementos y tendria un costo de O(n). Mayor en cómputo, menor en espacio ya que no tendria espacios vacíos.
Podria ser un árbol balanceado de búsqueda como treemap que no tendria un costo de espacio vacio exagerado y el costo seria de Log2N.

b)Elegiria una lista ya que hay que recorrer todos los items y no están ordenados por ese criterio O(n).
Si estuviesen ordenados por ese criterio elegiria un treemap O(log 2 n).

c)HashMap ya que el codigo postal seria la key y el value una lista de los clientes por lo que seria O(1)

No entiendo a que se refiere de mostrar gráficamente como se relacionan


Ejercicio 4.
Se desea desarrollar una aplicación para mejorar la atención de una biblioteca en cuanto a
la búsqueda de libros dentro del catálogo disponible. Cada libro estará compuesto por un
identificador único y datos propios de los libros (título, autor, géneros, año de publicación,
cantidad de ejemplares, etc.)
Se sabe, además, que los libros nuevos se agregan al catálogo en horarios fuera de la
atención al público.
Se desean proveer los siguientes servicios:
● Obtener la cantidad de ejemplares de un libro dado su identificador único.
● Obtener todos los libros de un género dado.
● Obtener todos los libros publicados entre dos años de publicación dados.
Responda y justifique:
1) ¿Qué estructura de datos utilizaría para almacenar todos los libros en memoria
dentro de la aplicación?
2) ¿Cómo resolvería cada uno de los servicios solicitados? ¿Utilizaría alguna estructura
adicional de acceso para mejorar el costo de respuesta de cada servicio?
     
Como estructura usaria HashMap para los dos primeros puntos ya que la key seria el id o el genero respectivamente.
Para la última usaría TreeMap ordenado por fecha ya que la busqueda pasaría de O(n) en otra estructura a O(log n)



*/
    
}
