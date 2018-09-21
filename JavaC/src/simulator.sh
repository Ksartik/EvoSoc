
#!/bin/bash
# First line of the script is shebang which tells the system how to execute
# the script: http://en.wikipedia.org/wiki/Shebang_(Unix)
# As you already figured, comments start with #. Shebang is also a comment.

# Simple hello world example:
echo This is scripting of a very exciting program : EvoSoc! # => Hello world!


# Each command starts on a new line, or after semicolon:
echo 'Print Works';
# => This is the first line
# => This is the second line
if [ -e "Simulator.java" ]; then
  echo "Simulaotr.java file exists"
fi

i=0
# for setting initial value of nHumans and changing it to analyze results....
x=300
var='nHumans'

for (( i = 0; i < 10; i++ )); do
    #statements
    javac Simulator.java 
    java Simulator
    result=$?
    echo $result
    echo "The number of persons alive in this simulations were ... ${result}"
    # Typical Synatx to change value of int of variable var in file Constants.java
	sed -i "s/.*$var.*/		public static final int $var = $x ;/g" Constants.java
	((x=x+100))
done

