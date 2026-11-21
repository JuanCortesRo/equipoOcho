# Guía de trabajo — EquipoOcho

La rúbrica califica dos cosas: el seguimiento de los **criterios** en Jira (Por hacer → En curso → Hecho) y la **evidencia de trabajo colaborativo en GitHub**. Por eso, el trabajo de cada integrante tiene que quedar en su propia rama, sus propios commits y su propio PR.

## 1. Ramas

| Rama | Para qué | Quién escribe |
|---|---|---|
| `main` | Versión entregable. Recibe solo el merge final de `develop` al cierre del sprint. | Nadie hace push directo |
| `develop` | Integración. Todas las features llegan aquí por PR. | Solo por PR |
| `feature/DE8-<n>-descripcion-corta` | Una HU o HT de Jira | El responsable |
| `fix/DE8-<n>-descripcion-corta` | Corrección de un bug | Cualquiera |
| `chore/descripcion-corta` | Configuración o documentación | Cualquiera |

- Usa minúsculas, guiones y sin tildes. Ejemplo: `feature/DE8-7-login-biometria`.
- Si la clave de Jira está en el nombre de la rama, Jira la enlaza automáticamente (requiere la app *GitHub for Jira*).
- Siempre se parte de `develop` actualizado:

```bash
git checkout develop && git pull
git checkout -b feature/DE8-7-login-biometria
```

- Antes de abrir el PR, trae los últimos cambios de `develop` a tu rama:

```bash
git fetch origin && git merge origin/develop   # o: git rebase origin/develop
```

## 2. Commits — Conventional Commits + clave de Jira

```
<tipo>(<alcance>): DE8-<n> <descripción en imperativo, minúsculas>
```

| Tipo | Uso |
|---|---|
| `feat` | Funcionalidad nueva (un criterio de una HU) |
| `fix` | Corrección de un bug |
| `refactor` | Cambio interno que no altera el comportamiento |
| `style` | Formato o estilos XML sin cambio funcional |
| `docs` | README u otra documentación |
| `chore` | Gradle, dependencias o configuración |

**Alcances:** `login`, `home`, `new-appointment`, `detail`, `edit`, `data`, `api`, `ui`, `nav`, `build`.

Ejemplos:
```
feat(login): DE8-8 muestra BiometricPrompt al tocar la huella
feat(new-appointment): DE8-25 deshabilita Guardar Cita si hay campos vacíos
fix(home): DE8-15 el botón atrás cierra la app en lugar de volver al login
chore(build): DE8-3 agrega dependencias de Room con KSP
```

**Hábitos**
- Haz commits pequeños, idealmente uno por criterio.
- Haz push al menos una vez al día.
- No hagas commits con el mensaje "cambios" o "wip".

## 3. Pull Requests
- **Origen:** tu rama `feature/...`. **Destino:** `develop`.
- **Título:** `DE8-<n> <tipo>(<alcance>): <resumen>`. Ejemplo: `DE8-7 feat(login): ventana de login con biometría`.
- Llena la plantilla (`.github/pull_request_template.md`): criterios cubiertos, capturas comparadas con la HU y cómo probar.
- **Al menos 1 aprobación** de otro integrante. Juan José revisa todos los PRs como integrador.
- **Tipo de merge: "Create a merge commit"** (no squash), para conservar el historial individual que se evalúa.
- Después del merge se borra la rama remota.

**Configuración sugerida en GitHub** (*Settings → Branches → Add rule*), para `main` y `develop`:
- *Require a pull request before merging*, con 1 aprobación.
- Bloquear el force push.

## 4. Jira

### Importar el backlog
El backlog del sprint se carga desde un archivo CSV que no está en el repositorio.

1. Crea el proyecto Scrum con la clave **DE8**.
2. Ve a **Configuración de Jira (⚙) → Sistema → Importación de sistemas externos → CSV**. En Jira Cloud también se llega por *Filtros → Importar issues desde CSV*.
3. Sube el CSV con codificación **UTF-8** y separador **coma**.
4. Mapeo de columnas:

   | Columna CSV | Campo Jira |
   |---|---|
   | Issue ID | Issue Id |
   | Parent | Parent Id |
   | Issue Type | Issue Type |
   | Summary | Summary |
   | Description | Description |
   | Priority | Priority |
   | Story Points | Story Points (o *Story point estimate* en proyectos team-managed) |
   | Labels | Labels |

5. Si la importación no crea el tipo "Sub-task", actívalo en la configuración del proyecto.

### Después de importar
1. **Asignar en bloque por label.** Filtra con, por ejemplo, `labels = alejandro-fernandez`, y usa *Bulk change → Assignee*:

   | Label | Integrante |
   |---|---|
   | `juan-jose-cortes` | Juan José Cortés Rodríguez |
   | `alejandro-fernandez` | Alejandro Fernández Herrera |
   | `jerson-otero` | Jerson David Otero Cruz |
   | `juan-pablo-robayo` | Juan Pablo Robayo Maestre |

2. Crea el **Sprint 1** (12-nov → 26-nov) y arrastra todas las historias.

### Flujo diario
- Cada **sub-task es un criterio de aceptación**:
  - Cuando empiezas un criterio, lo pasas a *En curso*.
  - Cuando su PR se mergea en `develop`, lo pasas a *Hecho*.
- La historia pasa a *Hecho* cuando todos sus criterios están en *Hecho* y se probó en `develop`.
- Las historias técnicas llevan el label `historia-tecnica`.
- Daily asíncrona (canal del equipo): *qué hice / qué haré / qué me bloquea*.

## 5. Calendario de cierre

| Fecha | Hito |
|---|---|
| 21-nov | Esqueleto en `develop`; backlog importado y asignado |
| 22-nov | HT-01, HT-02 y HT-03 mergeadas (Juan José) |
| 25-nov | Todas las HU mergeadas en `develop`; prueba integral en emulador |
| 26-nov, 12:00 | **Code freeze.** Solo se aceptan fixes críticos |
| 26-nov, antes de las 17:00 | PR `develop → main`, merge y verificación final; PDF subido al campus |
| 26-nov, 18:00 | **Cero commits** desde esta hora |
