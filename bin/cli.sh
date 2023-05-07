#!/usr/bin/env bash

set -euo pipefail

# load commands
###############

. "$(dirname "$0")/cmdExt/client.sh"
. "$(dirname "$0")/cmdExt/startServer.sh"
. "$(dirname "$0")/cmd/stopServer.sh"

# run main function
###################

main "$@"
