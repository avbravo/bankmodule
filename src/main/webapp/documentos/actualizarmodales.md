#ACTUALIZAR MODALES
para actualizar los modales use <p:autoUpdate>
   
<p:inputTextarea rows="1" cols="4" id="mytokeen" maxlength="4" class="board"
                 styleClass="board"
                 value="#{reinicioRemotoConfirmarController.tokenIngresado}"
                 >
    <p:autoUpdate />
</p:inputTextarea>