#FORMULARIO DE OPERACION éxitoSA

1.En el controller anterior guardar la accion reciente en JmoordbContext
     AccionReciente accionReciente = accionRecienteServices.create(agenda, bank, cajero, accion, grupoAccion, estado);
    JmoordbContext.put("accionReciente", accionReciente);

2. En el return del metodo

     emailServices.sendEmailToTecnicos(accionReciente, accion, user, cajero, bank);

    return "/faces/operacionéxitosa.xhtml";
   generalmente despues de enviar el email de notificaciones

3. Este formulario muestra la accion que se acaba de ejecutar
4. Desde este formulario se va directemente al dashboard
