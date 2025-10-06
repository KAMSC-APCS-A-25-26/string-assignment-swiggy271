#!/bin/bash
set -euo pipefail
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd)
PROJECT_ROOT=$(cd "$SCRIPT_DIR/.." && pwd)
cd "$PROJECT_ROOT"

printf "\n🧪 Running StringAssignment tests...\n\n"
MVN_CMD=(mvn -q -f "$PROJECT_ROOT/pom.xml" test)
"${MVN_CMD[@]}"

printf "\n✅ Tests finished. See target/surefire-reports for details.\n"
