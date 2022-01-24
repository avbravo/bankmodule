#SELECTONEMENU
si usa eventos ajax altera el css y lo muestra en  la esquina superior derecha
  <p:selectOneMenu id="selectOneMenuAccion" 
                 class="form-control rounded-pill solosel01"
                 value="#{reinicioRemotoController.selectOneMenuAccionValue}" 
                 converter="#{accionConverter}"
                 >
    <f:selectItem itemLabel="Seleccione una Accion" itemValue="#{null}"  noSelectionOption="true"/>
    <f:selectItems value="#{reinicioRemotoController.accionList}" 
                   var="var" 
                   itemValue="#{var}" itemLabel="#{var.ACCION} " />




    <p:ajax process="selectOneMenuAccion" event="change"
            update=":form, :form:growl"
            listener="#{reinicioRemotoController.onSelectOneMenuAccionChange()}"/>
</p:selectOneMenu>