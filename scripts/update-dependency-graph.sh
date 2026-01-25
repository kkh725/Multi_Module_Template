set -e

./gradlew dumpModuleDeps --no-configuration-cache

node scripts/generate-mermaid.js \
  build/module-deps.json \
  docs/module-deps.md
