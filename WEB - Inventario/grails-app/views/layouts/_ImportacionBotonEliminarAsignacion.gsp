<span class="izquierda">
    <g:form resource="${asignacion}" method="DELETE">
        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModalEliminar${asignacion.id}">
            <i class="glyphicon glyphicon-trash"></i>
            Eliminar
        </button>
        <!-- Modal -->
        <div class="modal fade" id="exampleModalEliminar${asignacion.id}" tabindex="-1"
             role="dialog"
             aria-labelledby="exampleModalLabel${asignacion.id}"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title"
                            id="exampleModalLabel">
                            <i class="glyphicon glyphicon-exclamation-sign"></i>
                            <strong>Alerta</strong>
                            <button type="button"
                                    class="close"
                                    data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">
                                    &times;
                                </span>
                            </button>
                        </h3>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-warning">
                            <strong>
                                ¿Está usted seguro que desea eliminar la asignación con el identificador ${asignacion.id}?
                            </strong>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <g:form resource="${asignacion}" method="DELETE">
                            <button type="submit" class="btn btn-danger" name="delete.id" id="delete" value="Delete">
                                <i class="glyphicon glyphicon-trash"></i> Eliminar
                            </button>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </g:form>
</span>