#Obtener listado de tecnicos

Se lee en el init  AccessContoller
Los valores se leen y se almacenan en JmoordbContext mediante 
        JsfUtil.propertiesBigIntegerToContext(prop, "grupoAccionBajarPlantillaId");

Para obtener los valores del Context
    JsfUtil.contextToBigInteger("grupoAccionEncenderSubirPlantillaId")
