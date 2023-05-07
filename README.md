# repo-module-ext
The extension repository to demonstrate using git submodules for cross repository dependencies

## Prerequisites
- Java 8+
- Maven 3.6+
- Jekyll 4+ (for building documentation)
- Golang 1.18+ (for building tarball)

## Operational workflow

### Developing on existing code
- Modify existing java code
- Build with `mvn clean install`
- Start server with `bin/cli.sh startServer`
- Run client with `bin/cli.sh runClient`
- Run additional client with `bin/cli.sh runClientFormal`
- Stop server with `bin/cli.sh stopServer`
- Build tarball with `scripts/build-tarball.sh`

### Adding a new java module
- Add module to `pom.xml` for maven to recognize
- (as needed) Add new CLI commands by adding a new file in `bin/cmd/yourModule.sh` and add an additional line in `bin/cli.sh` to source the file.
- Update `scripts/tarball-profile.yml` with the location of the new module's jar as well as any additional complementary files

### Updating the base repository
As new changes are introduced to the base repository, the extension repository will need to pick up updates.
To execute this update, update the submodule to point to the branch with the desired changes.
Since submodules cannot point to specific commit hashes, it is a best practice to point to a branch that is not expected to have additional commits pushed, such as a release branch.
Updating the submodule will automatically update the symlinks to multiple areas of the repository, but there are additional considerations:
- Update the `base.version` property in the root `pom.xml`. This is needed to resolve the correct version of the java modules from the base repository.
- Inspect for any changes to the corresponding proto module, since the .proto files in the ext repository are not linked to the base repository in any way.
- Ensure the java code still compiles and tests pass; updates may have introduced breaking changes.
- Inspect changes in other areas, such as the `bin/` or `docs/` directories, and determine if extension specific changes are warranted.

## Repo components

## Java protobuf/grpc module
Java files are under the `ext/` directory.
The `ext/proto/` directory defines a simple [grpc](https://grpc.io/) service using [protobuf](https://protobuf.dev/).
When building the java code through maven, java files are generated from the protobuf files.
The `ext/server/` and `ext/client/` directories define a simple server and client to demonstrate the use of grpc by importing the generated files.

### Extending proto
The extension repository keeps its own independent copy of the proto files.
A manual inspection of changes to the base repository's proto files is needed to add, remove, or modify the corresponding proto files in the extension repository.
TODO: Generate a script to produce the git diff between the previous state and current state of the base repository.

## Documentation
Documentation is tracked in the `docs/` directory.
See its [README](docs/README.md).

### Extending docs
Most configuration and layout files are linked to the base repository.
The docs content under the `files/` directory are linked file by file if derived from the base repository.
The general guideline is to never symlink directories; this is to ensure the extension repository is selecting the shared content file by file.
See the example of `docs/files/Dynamic.md` to see how to modify a specific section of a doc.

## CLI
A CLI is provided as a shell script, accessible through the entrypoint script at `bin/cli.sh`.
Read more about its implementation in its [README](cli/README.md).

### Extending CLI
The entrypoint script `bin/cli.sh` sources the command files one by one.
New command files can be added independently and sourced by the entrypoint script.

## Tarball generation
A tarball consisting of the necessary built files to independently run the project can be built by running the script at `scripts/build-tarball.sh`.
The code is written in golang and the script will build and run the go code internally.
Read about how to customize the tarball in its [README](scripts/README.md).

### Extending tarball script
The tarball generation logic is written in a generic way such that no extension-specific code changes are needed.
The contents of the generated tarball is controlled by `scripts/tarball-profile.yml`.
Similarly to proto files, this is an independent copy of the corresponding file in the base repository.
Upon update, there may be changes to the file that also need to be propagated to the extension repository's copy.

