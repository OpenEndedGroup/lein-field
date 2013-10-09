`-*- word-wrap: t; -*-`

lein-field
==========

Leiningen plugin for launching Field.

Build & install:

    lein install

Usage:

    lein new testproject
    cd testproject

In the `project.clj`:

          :plugins [[lein-field "0.3.0-SNAPSHOT"]]

To call:

    export FIELD=/home/marc/fieldwork/field/Contents/linux/field_linux64_8.sh
    lein field

Will launch Field "inside" this project. All project.clj `dependencies` will be visible on Field's classpath, all Field sheets will be stored in this project.

Optional command-line argument to specify the sheet name:

        lein field sheet-name

or

        lein field sheet-name.field

Options in `project.clj`:

    :field-arguments {:stereo 1 :opengl32 1} ; add arguments to Field's command line

Options can also be passed on the command line (useful for setting custom bindings in Field's `_self`)::

      lein field :foo bar :stereo 1 sheet-name

Command-line arguments override those in the project (which in turn override the hard-wired defaults).

If there's an odd number of arguments, the last one is assumed to be the sheet name. The rest of the arguments are passed through to Field verbatim (with `:foo` keyword syntax converted to `-foo`, as is done with `:field-arguments` in the project.) The sheet can always be specified with `:field.scratch`, although this is interpreted directly without any automatic addition of a `.field` suffix.

Coming soon: Syntax Highlighting (borrowed from arthuredelstein/clooj) and completion (borrowed from alexander-yakushev/compliment).
