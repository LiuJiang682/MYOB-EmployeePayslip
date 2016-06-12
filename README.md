# MYOB-EmployeePayslip

This is the code exercise for MYOB. It is with the brief provided as follows:

Problem: Employee monthly payslip

When I input the employee's details: first name, last name, annual salary(positive integer) and super rate(0% - 50% inclusive), payment start date, the program should generate payslip information with name, pay period,  gross income, income tax, net income and super.

The calculation details will be the following:
•       pay period = per calendar month
•       gross income = annual salary / 12 months
•       income tax = based on the tax table provide below
•       net income = gross income - income tax
•       super = gross income x super rate

Notes: All calculation results should be rounded to the whole dollar. If >= 50 cents round up to the next dollar increment, otherwise round down.

The following rates for 2012-13 apply from 1 July 2012.

Taxable income   Tax on this income
0 - $18,200     Nil
$18,201 - $37,000       19c for each $1 over $18,200
$37,001 - $80,000       $3,572 plus 32.5c for each $1 over $37,000
$80,001 - $180,000      $17,547 plus 37c for each $1 over $80,000
$180,001 and over       $54,547 plus 45c for each $1 over $180,000

Example Data
Employee annual salary is 60,050, super rate is 9%, how much will this employee be paid for the month of March ?
•       pay period = Month of March (01 March to 31 March)
•       gross income = 60,050 / 12 = 5,004.16666667 (round down) = 5,004
•       income tax = (3,572 + (60,050 - 37,000) x 0.325) / 12  = 921.9375 (round up) = 922
•       net income = 5,004 - 922 = 4,082
•       super = 5,004 x 9% = 450.36 (round down) = 450

Here is the csv input and output format we provide. (But feel free to use any format you want)

Input (first name, last name, annual salary, super rate (%), payment start date):
David,Rudd,60050,9%,01 March – 31 March
Ryan,Chen,120000,10%,01 March – 31 March

Output (name, pay period, gross income, income tax, net income, super):
David Rudd,01 March – 31 March,5004,922,4082,450
Ryan Chen,01 March – 31 March,10000,2696,7304,1000

As part of your solution:
•       List any assumptions that you have made in order to solve this problem.
•       Provide instruction on how to run the application
•       We value good design and test, it’s important to us

Here are the assumes:

1. The program is run on Java 8.
2. The user is has the right to access the disk of computer this program is running on.
3. The input file has a reasonable size. The program has default of 6000 records, please over ride the third parameter record size if your file's records is greater than 6000. The program only read the file once, it will not goes back to file for the second read when it reach the max record. 

To run this program, you need to do the following:
1. Download the MYOB-EmployeePayslip.zip file
2. Get a command prompt.
3. cd your-download-directory
4. Unzip the zip file.
5. run the program as:
   java -jar EmployeePayslip-1.0.0-jar-with-dependencies.jar
or
   java -jar EmployeePayslip-1.0.0-jar-with-dependencies.jar inputFile
or
   java -jar EmployeePayslip-1.0.0-jar-with-dependencies.jar inputFile outputFile
or
    java -jar EmployeePayslip-1.0.0-jar-with-dependencies.jar inputFile outputFile 8000
    The default input/output file will be in src/test/resources directory.
6. if you have maven installed, run mvn clean install to build your own or
    mvn assembly:single to build the full executable jar.