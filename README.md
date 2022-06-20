Quotes

sistema genérico a cualquier tipo de industria, en el cual se puede realizar cotizaciones a diferentes proveedores, permitiendo escoger el que mejor se ajuste a las necesidades de la empresa. El sistema permitirá el registro de diferentes proveedores que desean ofertas sus productos, para facilitar la integración de los diferentes proveedores se expondrán servicios web y de esta forma permitir la postulación de las ofertas de forma automática.
Se implementarán la siguiente funcionalidad:
1. Solicitar productos. Permite a un usuario de autorizado solicitar una cantidad de producto requerido. La solicitud deberá integrar el mayor detalle, se parametrizaran las categorías y los diferentes productos para que sean seleccionados. Una vez se crea la solicitud, esta quedara en estado abierto hasta que se llega a la fecha final parametrizada, en cuyo caso no se recibirán más ofertas de los proveedores.
2. Consultar ofertas. Permite a un usuario autorizado de consultar las solicitudes que se encuentran abiertas y ver las ofertas que tienen asociadas.
3. Enviar oferta. Permite a un proveedor autorizado enviar una oferta por una solicitud de adquisición de producto en estado abierta. El sistema debe registrar todas las ofertas realizadas.
4. Emitir orden de compra al proveedor seleccionado. Cuando se llega a la fecha límite. El sistema verifica la oferta con menor valor y envía al proveedor seleccionado una orden de compra por el producto y cantidad de la solicitud
5. Actualizar inventario de productos. Después de que se termina la solicitud el sistema actualiza el inventario interno.
Como este nuevo mecanismo se espera se convierta en una estrategia para soportar el proceso de negocio de la compañía, se espera que el sistema provea la siguiente funcionalidad:
• El sistema debe estar disponible 24x7.
• Se estima que al sistema accederán 50 usuarios de manera concurrente.
• El sistema soportará el registro de categorías y productos a necesidad de la organización.
• El sistema debe permitir la actualización de los inventarios de la organización.
• El mecanismo de integración que los proveedores usarán para realizar las ofertas deben responder en menos de 5 segundos.
