## Historia / tarea
<!-- Clave de Jira en el título del PR: "DE8-12 feat(login): autenticación biométrica" -->
- Jira: DE8-
- HU / HT: <!-- p. ej. HU 1.0 Ventana Login -->

## Criterios de aceptación cubiertos
<!-- Marca solo los que este PR deja terminados y mueve su sub-task a "Hecho" en Jira -->
- [ ] Criterio 1:
- [ ] Criterio 2:

## Cambios
<!-- Qué se hizo y por qué, en 2-5 viñetas -->
-

## Capturas
<!-- Obligatorio si el PR toca UI: compárala con el pantallazo de la HU -->
| HU (referencia) | Implementado |
|---|---|
|  |  |

## Cómo probar
1.

## Checklist
- [ ] La rama sale de `develop` y apunta a `develop`
- [ ] Compila (`./gradlew assembleDebug`) y la app corre en emulador o dispositivo
- [ ] Respeta MVVM: el Fragment no accede a Room ni a Retrofit, solo al ViewModel
- [ ] Textos en `strings.xml` y colores en `colors.xml` (nada hardcodeado)
- [ ] No modifiqué contratos compartidos (entidad, DAO, repositorios, nav_graph) sin avisar al equipo
- [ ] Commits con formato `tipo(alcance): DE8-<n> descripción`
