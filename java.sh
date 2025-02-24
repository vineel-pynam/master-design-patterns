#!/bin/bash
# script for running java files

# HOW TO RUN
# Go to root folder in terminal, type ./java.sh FileName.java (if the are multiple files in the root directory, it will ask for selection.)

# Define the base directory dynamically
# REPLACE WITH OUR ROOR DIRECTORY
BASE_DIR=/home/vineel/Projects/master-design-patterns

runjava() {
    local filename="$1"

    # Find all matching Java files
    files=($(find "$BASE_DIR" -type f -name "$filename"))

    # If no file found
    if [[ ${#files[@]} -eq 0 ]]; then
        echo "Error: File '$filename' not found in the project."
        return 1
    fi

    # If multiple files are found, prompt user to choose
    if [[ ${#files[@]} -gt 1 ]]; then
        echo "Multiple matches found. Please select the correct file:"
        select file_path in "${files[@]}"; do
            [[ -n "$file_path" ]] && break
        done
    else
        file_path="${files[0]}"
    fi

    # Extract package name from the Java file
    package_name=$(grep -oP '^package\s+\K[\w.]+(?=;)' "$file_path")

    # Extract class name (remove .java extension)
    class_name="${filename%.java}"

    # Compile the Java file
    javac "$file_path"

    # If compilation was successful, run the program
    if [[ $? -eq 0 ]]; then
        java "$package_name.$class_name"

        # Cleanup: Remove all generated .class files in the package directory
        find "$(dirname "$file_path")" -type f -name "*.class" -delete
    fi
}

# Ensure a Java filename is provided
if [[ -z "$1" ]]; then
    echo "Usage: ./runjava.sh <JavaFileName.java>"
    exit 1
fi

# Call function with the Java file name as an argument
runjava "$1"