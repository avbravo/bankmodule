#IF ELSE JSF


<c:if test="${!cajeroEncontradoController.haveAccionReciente}">
    <p class="atm-title-mar01"><strong>No hay acciones agendadas</strong></p>
</c:if>

 <c:if test="${cajeroEncontradoController.haveAccionReciente}">
    <dd class="atm-title-mar01"><strong>#{cajeroEncontradoController.accionReciente.TITULO}</strong></dd>
</c:if>