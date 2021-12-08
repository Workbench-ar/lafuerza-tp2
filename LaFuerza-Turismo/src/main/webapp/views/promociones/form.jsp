<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="Tipo_Promocion"
			class='col-form-label ${promocion.errors.get("tipoPromocion") != null ? "is-invalid" : "" }'>Tipo de promoción:</label>
		<input class="form-control" type="number" id="Tipo_Promocion" name="Tipo_Promocion"
			required value="${promocion.tipoPromocion}"></input>
		<div class="invalid-feedback">
			<c:out value='${promocion.errors.get("tipoPromocion")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="Tipo_Atracciones"
			class='col-form-label ${promocion.errors.get("tipoAtracciones") != null ? "is-invalid" : "" }'>Tipo de atracciones:</label>
		<input class="form-control" type="number" id="Tipo_Atracciones" name="Tipo_Atracciones"
			required value="${promocion.tipoAtracciones}"></input>
		<div class="invalid-feedback">
			<c:out value='${promocion.errors.get("tipoAtracciones")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="nombre" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="nombre" name="nombre"
			required value="${promocion.nombre}">
	</div>
	<div class="mb-3">
		<label for="descripcion"
			class='col-form-label ${promocion.errors.get("descripcion") != null ? "is-invalid" : "" }'>Descripción:</label>
		<input class="form-control" type="text" id="descripcion" name="descripcion"
			required value="${promocion.descripcion}"></input>
		<div class="invalid-feedback">
			<c:out value='${promocion.errors.get("descripcion")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="variable"
			class='col-form-label ${promocion.errors.get("variable") != null ? "is-invalid" : "" }'>Variable:</label>
		<input class="form-control" type="number" id="variable" name="variable"
			required value="${promocion.variable}"></input>
		<div class="invalid-feedback">
			<c:out value='${promocion.errors.get("variable")}'></c:out>
		</div>
	</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
