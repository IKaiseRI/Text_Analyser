# Text Analyser

***This repository contains the code for the technical test for the Stefanini internship. The project includes a .jar file that can be executed with three arguments to analyze text from a specified file.***

## How to Run

***To run the Text Analyser, use the following command format:***

*java -jar .\Text_Analyser-1.jar -file=<absolute_path_to_file> -top=<number_of_top_phrases> -phraseSize=<phrase_size>*

## Example Command

*java -jar .\Text_Analyser-1.jar -file=C:\Users\Legion\IdeaProjects\Text_Analyser\resources\text.txt -top=5 -phraseSize=3*

## Command Line Arguments

+ -file=: This is the absolute path of the file that will be analyzed. It must not be null and must not contain spaces.
+ -top=: This value represents the number of top phrases that will be output. It must be a non-null, non-negative number.
+ -phraseSize=: This value represents the size of the phrases that will be extracted from the text. It must be a non-null, non-negative number.

## Argument Validation

***The code includes validation for all three arguments and provides exception messages for invalid cases. Ensure that the provided values meet the following criteria:***

+ -file: Must be a valid, non-null path without spaces.
+ -top: Must be a non-null, non-negative number.
+ -phraseSize: Must be a non-null, non-negative number.
