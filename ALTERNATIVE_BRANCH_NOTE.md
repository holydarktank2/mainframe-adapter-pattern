This branch intentionally demonstrates an alternative architecture from the existing implementation.

Added in this commit:
- `AlternativeProfileConversionFacade` using:
  - profile registry
  - enrichment pipeline
  - mapping rules
  - conversion engine (fail-fast required-field validation)

Goal:
- make branch differences visible
- provide a different solution shape for conversion-layer design
