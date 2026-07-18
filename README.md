# **🛡️ Recuperatorio del 1er Parcial de Programación con Objetos II \- Com. 3 (90 Minutos).**

\[\!IMPORTANTE\]  
👀 Leer todo antes de comenzar a resolver

## **⚙️ Datos necesarios y obligatorios a completar.**

* **APELLIDO, NOMBRE**: **Pablo Elias Andino Guash** 
* **COMISIÓN**: **3** 
* **DNI**: **46011207**

## **📝 Consideraciones Iniciales y Criterio de Evaluación.**

Se evaluará cada solución prestando especial atención a:

* **Pautas obligatorias** (descritas abajo) correctamente cumplidas.  
* **Entendimiento y correcta aplicación de los conceptos vistos**: Patrones de diseño (Strategy, Template Method, Decorator), reificación, manejo de excepciones, test unitarios (**GWT** y **AAA**).  
* **Prolijidad y legibilidad** del código presentado.  
* El código entregado debe tener los tests suficientes que garanticen el correcto funcionamiento (*esperado 75%+*).  
* La solución debe aplicar patrones de diseño apropiados. **El uso inadecuado de patrones descalifica el examen automáticamente**.  
* Las entregas que tengan un solo commit o no reflejen el progreso serán desaprobadas. **Realizar commits/push periódicamente**.

## **📌 Pautas obligatorias para la entrega.**

Utilizaremos un sistema de 3 'checkpoints', a saber:

* ⚠️ El código entregado debe compilar obligatoriamente. **Un parcial entregado cuyo código no compila queda desaprobado automáticamente**.  
* **Checkpoint 1**: Push inicial completando los datos personales.  
* **Checkpoint 2**: Push antes de realizar el primer test.  
* **Checkpoint 3**: Push al final de la entrega, al terminar sus test.

# **🚨 Centro de Operaciones de Ciberdefensa (COC) 🌐.**

Un departamento de ingeniería en ciberdefensa nos solicitó modelar el motor central de su sistema SIEM (Security Information and Event Management). El sistema asigna alertas de seguridad a los analistas del SOC basándose en su especialidad, enriqueciendo la información del evento antes de entregarlo para facilitar el análisis forense y la mitigación.

# **🕵️‍♂️ Criterios de Triaje (Especialidad del Analista).**

Cada analista tiene configurado un **criterio de triaje** que define qué tipo de alertas le son asignables. Los criterios actuales son:

* 🟢 **Analista Nivel 1 (Triage General)**: Puede procesar cualquier alerta. Es el criterio por defecto para los analistas junior.  
* 🔴 **Especialista en Amenazas Críticas**: Solo procesa alertas cuyo nivel de severidad base sea mayor a 75\.  
* 🏢 **Especialista en Infraestructura**: Solo procesa alertas cuyo origen provenga de una lista de IPs de infraestructura crítica (ej: "192.168.0.1", "10.0.0.5"). La lista de IPs debe ser configurable.  
* 🛡️ **Defensa Híbrida**: Procesa las alertas que cumplen con el criterio de "Especialista en Amenazas Críticas" y también las del "Especialista en Infraestructura". Ambas condiciones por separado son suficientes.

# **⚠️ Alertas de Seguridad.**

Las alertas generadas por los sensores de red tienen definido:

* 📛 **código identificador** (ej: "CVE-2024-123")  
* 🌐 **IP de origen**  
* 📊 **severidad base** (un valor numérico)  
* 🕰️ **tiempo de detección** (en milisegundos)

Por ahora solo existen alertas de red estándar (podemos llamarle simplemente *Alerta*), aunque en el futuro podrían integrarse alertas de endpoints u otros sensores.  
Una alerta se considera **crítica por defecto** si su severidad base es superior a 90\.

# **➕ Enriquecimiento de Inteligencia (Threat Intel).**

El sistema puede enriquecer dinámicamente una alerta agregando módulos de inteligencia antes de ser analizada por el SOC. Cada módulo suma puntos a la severidad total de la alerta. Se conocen los siguientes:

* 📍 **Geolocalización de IP** → suma \+5 a la severidad total.  
* 📜 **Historial Forense** → suma \+15 a la severidad total.  
* 🦠 **Análisis de Malware en Sandbox** → suma \+35 a la severidad total.

El sistema debe estar preparado para agregar nuevos módulos de inteligencia en el futuro sin modificar las clases existentes.

# **👥 Analistas del SOC.**

De los analistas nos interesa conocer:

* 🪪 **Legajo** (nunca puede modificarse)  
* 🎯 **Criterio de triaje** (puede cambiar en cualquier momento según las necesidades del turno)  
* 📜 **Historial de alertas procesadas**.

Cada analista debe poder informar la lista de sus alertas procesadas, incluyendo la severidad total (con los enriquecimientos aplicados) de cada una.

# **🖥️ El Motor SIEM.**

El motor SIEM administra el flujo de respuesta a incidentes y mantiene la cola de alertas pendientes.

## **Proceso de Asignación de Alertas.**

Cuando el sistema ejecuta la acción de **asignar trabajo a un analista**, ocurre la siguiente secuencia inalterable:

1. Busca en la cola la **primera alerta** que cumpla con el criterio del analista.  
2. Aplica módulos de **enriquecimiento de inteligencia** a la alerta (según la estrategia global de enriquecimiento configurada en el SIEM).  
3. Asigna la alerta enriquecida al historial del analista (removiéndola de la cola pendiente).

El flujo de estos tres pasos es fijo, pero el paso 2 (qué módulos se agregan) depende de la configuración del SIEM. Las estrategias de enriquecimiento son:

* **Modo Rápido**: No se aplica ningún enriquecimiento.  
* **Modo Forense**: Se aplica la Geolocalización y el Historial Forense.

# **❌ Situaciones Excepcionales.**

* AlertaInvalidaException: No se puede crear una alerta con severidad negativa, código vacío o IP vacía.  
* AnalistaSinCriterioException: Si se intenta asignar trabajo a un analista sin un criterio de triaje configurado.  
* EnriquecimientoIncompatibleException: Algunos enriquecimientos no son compatibles. Por ejemplo, el "Análisis de Malware en Sandbox" no se puede aplicar a alertas cuya IP de origen sea de localhost ("127.0.0.1").

# **🏗️ Restricciones de Diseño.**

* No se permite modificar la alerta base original al agregar módulos de inteligencia; la inmutabilidad de la evidencia es vital.  
* El sistema debe poder sumar nuevos módulos de enriquecimiento sin romper código existente.  
* No se permite el uso de estructuras condicionales (if/switch) para despachar el comportamiento de los criterios o los enriquecimientos.  
* El flujo de asignación del Motor SIEM debe respetar su secuencia fija permitiendo variar la estrategia de enriquecimiento.
