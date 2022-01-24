# Dashboard

Calcular TotalesGrupo Estado:

- [x]   El archivo de propiedades configuration.proterties --> contiene la configuracion de codigosgrupos de grupoestado
| Key | Value |
| --- | ----------- |
| grupoEstadoSolicitadoId | 1 |
| grupoEstadoSolicitadoId| 2 |
| grupoEstadoFinalizadoId| 3 |
| grupoEstadoNoSepuedejecutarId |4 |

- [x] En AccessContoller se leen las propiedades y se guardan en JnoordbContext para cada una.

- []   La Inteface Dashboard calcula los totales por grupo y los guarda en un List<GrupoEstado>

Ejemplo del metodo para hacer las implementaciones de encontrar totales

```
public String onCommandButttonCalcularTotales() {
        try {
            grupoEstadoList = dashboardServices.calcularTotalGrupoEstado(bank);

            BigInteger totalSolicitado = dashboardServices.totalSolicitado(grupoEstadoList);
            BigInteger totalFinalizado = dashboardServices.totalFinalizado(grupoEstadoList);
            BigInteger totalEnProceso = dashboardServices.totalEnProceso(grupoEstadoList);
            BigInteger totalNoSePuedeEjecutar = dashboardServices.totalNoSePuedeEjecutar(grupoEstadoList);

        } catch (Exception e) {
            JsfUtil.errorMessage("onCommandButttonCalcularTotales()" + e.getLocalizedMessage());
        }
        return "";
    }
```
