#!/bin/bash
set -euo pipefail
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd)
PROJECT_ROOT=$(cd "$SCRIPT_DIR/.." && pwd)
cd "$PROJECT_ROOT"

printf "\n🧪 Running PalindromeChecker tests...\n\n"
mvn -q -f "$PROJECT_ROOT/pom.xml" -Dtest=PalindromeCheckerTest test

printf "\n✅ Tests finished. See target/surefire-reports for details.\n"
