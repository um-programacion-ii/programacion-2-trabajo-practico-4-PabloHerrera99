name: Nuevo Issue
description: Template para crear nuevos issues del sistema
title: "[ISSUE] Escribe un título claro aquí"
labels: [enhancement]
assignees: [tu-usuario-github]

body:
  - type: markdown
    attributes:
      value: |
        ## Descripción
        Describe brevemente el propósito de este issue.

  - type: textarea
    id: requisitos
    attributes:
      label: Requisitos Técnicos
      description: Lista los requisitos técnicos que debe cumplir.
      placeholder: Requisito 1, Requisito 2...
    validations:
      required: false

  - type: textarea
    id: criterios
    attributes:
      label: Criterios de Aceptación
      description: Define los criterios para considerar este issue como completo.
      placeholder: Criterio 1, Criterio 2...
    validations:
      required: false

  - type: textarea
    id: dependencias
    attributes:
      label: Dependencias
      description: ¿Este issue depende de otros?
      placeholder: Lista de issues o tareas relacionadas
    validations:
      required: false

  - type: dropdown
    id: complejidad
    attributes:
      label: Complejidad
      options:
        - Baja
        - Media
        - Alta
    validations:
      required: false

  - type: checkboxes
    id: etiquetas
    attributes:
      label: Labels sugeridas
      options:
        - label: enhancement
        - label: bug
        - label: documentation
        - label: testing
        - label: model
        - label: repository
        - label: service
        - label: controller
        - label: database

  - type: input
    id: milestone
    attributes:
      label: Milestone
      placeholder: Nombre del milestone

  - type: input
    id: proyecto
    attributes:
      label: Proyecto
      placeholder: Sistema de Gestión de Biblioteca

  - type: dropdown
    id: estado
    attributes:
      label: Estado inicial
      options:
        - To Do
        - In Progress
        - Done
