Feature: Gestion de usuarios en Petstore

  @CreateUser
  Scenario Outline: Crear usuario con POST /user
    Given el consumidor consulta el servicio de usuarios de Petstore
    When registra un usuario con username "<username>", nombre "<firstName>", apellido "<lastName>", correo "<email>", password "<password>", telefono "<phone>" y estado <status>
    Then el servicio responde con estado <createStatusCode>

    Examples:
      | username               | firstName | lastName | email                          | password | phone      | status | createStatusCode |
      | matias_api_create_01   | Matias    | Nunez    | matias.api.create01@mail.com   | pass1234 | 3001112233 | 1      | 200              |

  @GetUser
  Scenario Outline: Consultar usuario con GET /user/{username}
    Given el consumidor consulta el servicio de usuarios de Petstore
    And registra un usuario con username "<username>", nombre "<firstName>", apellido "<lastName>", correo "<email>", password "<password>", telefono "<phone>" y estado <status>
    And el servicio responde con estado <createStatusCode>
    When consulta el usuario "<username>"
    Then el servicio responde con estado <getStatusCode>
    And la respuesta contiene el username "<username>"

    Examples:
      | username            | firstName | lastName | email                       | password | phone      | status | createStatusCode | getStatusCode |
      | matias_api_get_01   | Matias    | Nunez    | matias.api.get01@mail.com   | pass1234 | 3001112233 | 1      | 200              | 200           |

  @UpdateUser
  Scenario Outline: Actualizar usuario con PUT /user/{username}
    Given el consumidor consulta el servicio de usuarios de Petstore
    And registra un usuario con username "<username>", nombre "<firstName>", apellido "<lastName>", correo "<email>", password "<password>", telefono "<phone>" y estado <status>
    And el servicio responde con estado <createStatusCode>
    When actualiza el usuario "<username>" con nombre "<updatedFirstName>", apellido "<updatedLastName>", correo "<updatedEmail>", password "<updatedPassword>", telefono "<updatedPhone>" y estado <updatedStatus>
    Then el servicio responde con estado <updateStatusCode>
    When consulta el usuario "<username>"
    Then el servicio responde con estado <getAfterUpdateStatusCode>
    And la respuesta contiene el nombre "<updatedFirstName>" y el correo "<updatedEmail>"

    Examples:
      | username               | firstName | lastName | email                          | password | phone      | status | updatedFirstName | updatedLastName | updatedEmail                   | updatedPassword | updatedPhone | updatedStatus | createStatusCode | updateStatusCode | getAfterUpdateStatusCode |
      | matias_api_update_01   | Matias    | Nunez    | matias.api.update01@mail.com   | pass1234 | 3001112233 | 1      | MatiasQA         | NunezQA         | matias.api.update01.qa@mail.com | pass5678        | 3009998877   | 1             | 200              | 200              | 200                      |

  @DeleteUser
  Scenario Outline: Eliminar usuario con DELETE /user/{username}
    Given el consumidor consulta el servicio de usuarios de Petstore
    And registra un usuario con username "<username>", nombre "<firstName>", apellido "<lastName>", correo "<email>", password "<password>", telefono "<phone>" y estado <status>
    And el servicio responde con estado <createStatusCode>
    When elimina el usuario "<username>"
    Then el servicio responde con estado <deleteStatusCode>
    And la respuesta contiene el mensaje "<username>"

    Examples:
      | username               | firstName | lastName | email                          | password | phone      | status | createStatusCode | deleteStatusCode |
      | matias_api_delete_01   | Matias    | Nunez    | matias.api.delete01@mail.com   | pass1234 | 3001112233 | 1      | 200              | 200              |
