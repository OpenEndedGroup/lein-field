lein-field
==========

Leiningen plugin for launching Field.

Build & install:

    lein install

Usage:

    lein new testproject
    cd testproject

    export FIELD=/home/marc/fieldwork/field/Contents/linux/field_linux64_8.sh
    lein field

Will launch Field "inside" this project. All project.clj `dependencies` will be visible on Field's classpath, all Field sheets will be stored in this project.

Optional command-line argument to specify the sheet name:

        lein field foo

or

        lein field foo.field
        
Then add `:plugins [[lein-field "0.1.0-SNAPSHOT"]]` to your project.clj.

Options:

    :field-arguments {:stereo 1 :opengl32 1} ; add arguments to Field's command line

Coming soon: Syntax Highlighting (borrowed from arthuredelstein/clooj) and completion (borrowed from alexander-yakushev/compliment).
