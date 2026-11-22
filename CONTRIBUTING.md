# Convenciones de trabajo del Equipo

## 1. Ramas

| Rama | Para qué | Quién escribe |
|---|---|---|
| `main` | Versión entregable. Recibe solo el merge final de `develop` al cierre del sprint. | Nadie hace push directo |
| `develop` | Integración. Todas las features llegan aquí por PR. | Solo por PR |
| `feature/KAN-<n>-descripcion-corta` | Una HU o HT de Jira | El responsable |
| `fix/KAN-<n>-descripcion-corta` | Corrección de un bug | Cualquiera |
| `chore/descripcion-corta` | Configuración o documentación | Cualquiera |

- Usa minúsculas, guiones y sin tildes. Ejemplo: `feature/KAN-7-login-biometria`.
- Si la clave de Jira está en el nombre de la rama, Jira la enlaza automáticamente (requiere la app *GitHub for Jira*).
- Siempre se parte de `develop` actualizado:

```bash
git checkout develop && git pull
git checkout -b feature/KAN-7-login-biometria
```

- Antes de abrir el PR, trae los últimos cambios de `develop` a tu rama:

```bash
git fetch origin && git merge origin/develop   # o: git rebase origin/develop
```

## 2. Commits — Conventional Commits + clave de Jira

```
<tipo>(<alcance>): KAN-<n> <descripción en imperativo, minúsculas>
```

| Tipo | Uso |
|---|---|
| `feat` | Funcionalidad nueva (un criterio de una HU) |
| `fix` | Corrección de un bug |
| `refactor` | Cambio interno que no altera el comportamiento |
| `style` | Formato o estilos XML sin cambio funcional |
| `docs` | README u otra documentación |
| `chore` | Gradle, dependencias o configuración |

Ejemplos:
```
feat(login): KAN-8 muestra BiometricPrompt al tocar la huella
feat(new-appointment): KAN-25 deshabilita Guardar Cita si hay campos vacíos
fix(home): KAN-15 el botón atrás cierra la app en lugar de volver al login
chore(build): KAN-3 agrega dependencias de Room con KSP
```

## 3. Pull Requests
- **Origen:** tu rama `feature/...`. **Destino:** `develop`.
- **Título:** `KAN-<n> <tipo>(<alcance>): <resumen>`. Ejemplo: `KAN-7 feat(login): ventana de login con biometría`.
- Llena la plantilla (`.github/pull_request_template.md`): criterios cubiertos, capturas comparadas con la HU y cómo probar.
- **Al menos 1 aprobación** de otro integrante. Juan José revisa todos los PRs como integrador.
- **Tipo de merge: "Create a merge commit"** (no squash), para conservar el historial individual que se evalúa.
- Después del merge se borra la rama remota.

## 4. Jira

### Flujo diario
- Cada **sub-task es un criterio de aceptación**:
  - Cuando empiezas un criterio, lo pasas a *En curso*.
  - Cuando su PR se mergea en `develop`, lo pasas a *Hecho*.
- La historia pasa a *Hecho* cuando todos sus criterios están en *Hecho* y se probó en `develop`.
- Las historias técnicas llevan el label `historia-tecnica`.
