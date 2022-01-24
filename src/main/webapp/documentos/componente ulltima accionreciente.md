#Ultima Accion Reciente CajeroEncontrado.java

Este componente es el que se muestra en los formularios individuales con la ultima opcion de ese cajero.

Refactorice el componente resources/ultimaacciongenerada/panelultimaacciongenerada.xhtml 
para adaptarlo al formulario en que este trabajando.


Definir 
  Boolean haveAccionReciente = Boolean.FALSE;


en el init
 @PostConstruct
    public void init() {
  findAccionReciente();
}

 private String findAccionReciente(){
        try {
            Optional<AccionReciente> accionRecienteOptional = accionRecienteRepository.findByBancoIdAndCajeroIdUltimaAccionReciente(bank.getBANCOID(), cajero.getCAJEROID());
            if(accionRecienteOptional.isPresent()){
                accionReciente = accionRecienteOptional.get();
                haveAccionReciente = Boolean.TRUE;
                JsfUtil.successMessage("Si tiene accion");
                 PrimeFaces.current().ajax().update("form:growl");
             //    PrimeFaces.current().ajax().update("growl");
            }else{
                System.out.println("No tiene acción reciente.....");
                JsfUtil.successMessage("Noi tiene accion");
                PrimeFaces.current().ajax().update("form:growl");
//                PrimeFaces.current().ajax().update("growl");
            }
        } catch (Exception e) {
             JsfUtil.errorMessage("findAccionReciente()" + e.getLocalizedMessage());
             PrimeFaces.current().ajax().update("form:growl");
        //    PrimeFaces.current().ajax().update("growl");
             
        }
        return "";
    }


public Boolean renderedByEstadoSolicitado(){
        return accionRecienteServices.renderedByEstadoSolicitado(accionReciente);
       
    }


 #Agregar el componente
resources/extension/panelultimaacciongenerada.xhtml



#En el formulario
  <extension:panelultimaacciongenerada id="panelultimaacciongenerada"
         rendered="#{cajeroEncontradoController.haveAccionReciente}"
  />




En el facade hacemos una cosulta ordenada por AGENDAID DESC y obtenemos el primer registro
 public Optional<AccionReciente> findByBancoIdAndCajeroIdUltimaAccionReciente(BigInteger BANCOID, BigInteger CAJEROID) {
    
        try {

            Query query = em.createQuery("SELECT a FROM AccionReciente a WHERE a.BANCOID = :BANCOID AND a.CAJEROID = :CAJEROID ORDER BY a.AGENDAID DESC");
            query.setParameter("BANCOID", BANCOID).setParameter("CAJEROID", CAJEROID);
            query.setFirstResult(1);
            query.setMaxResults(1);
            AccionReciente accionReciente =(AccionReciente) query.getSingleResult();
              return Optional.of(accionReciente);
        } catch (Exception ex) {
            System.out.println("findByBancoIdAndCajeroId() " + ex.getLocalizedMessage());
            JsfUtil.errorMessage("findByBancoIdAndCajeroId() " + ex.getLocalizedMessage());
        }
     return Optional.empty();
    }