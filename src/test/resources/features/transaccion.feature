Feature: Gestión de transacciones en Wompi
  Como comercio integrado a Wompi
  Quiero crear y consultar transacciones
  Para validar que los pagos queden correctamente registrados en el sistema

  Background:
    Given el comercio esta habilitado para operar en Wompi

  @smoke
  Scenario: Crear y consultar una transaccion bancaria exitosamente
    Given el usuario  obtiene los tokens legales requeridos
    When el usuario crea una transaccion bancaria con datos validos
    And el usuario consulta la transaccion creada
    Then la transaccion existe y contiene la informacion esperada