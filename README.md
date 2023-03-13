# Lambdas, Streams , new Date APIs, Optionals and Parallel programming in Java 8
> https://www.udemy.com/course/modern-java-learn-java-8-features-by-coding-it

Imperative programming:</br>
* How to perform tasks (algorithms) and how to track changes in state.</br>
* Object mutability.</br>
* Step by step instructions on how to achieve an objective.</br>
* Write the code on what needs to be done in each step.</br>

Declarative programming:</br>
* Focus on the result.</br>
* Object immutability.</br>
* Analogous to SQL.</br>

Example 1:

Imperative:</br>
```int sum=0;
for( int i=0;i<=100;i++ ) { sum+=i; }
```

Declarative:</br>
```
int sum = IntStream.rangeClosed(0,100).map(Integer::new).sum();
```

Example 2:

Imperative:</br>
```
List<Integer> integerList =Arrays.asList(1,2,3,4,4,5,5,6,7,7,8,9,9);
List<Integer> uniqueList = new ArrayList<>();
for( Integer i :integerList ) { if(!uniqueList.contains(i)) uniqueList.add(i); }
```

Declarative:</br>
```List<Integer> uniqueList = integerList.stream().distinct().collect(toList());```

## 1. Lambda
`Equivalent to a function (method) without a name, also referred as Anonymous functions.`

* Used to implement Functional Interfaces.</br>

* Expression: 
```
 ( )    ->      { }
Input  Arrow   Body
```
```
() -> Single Statement or Expression; // {} curly braces aren't needed
```
```
() -> { Multiple Statement or Expressions }; //  curly braces are needed
```

### Examples</br>

#### Runnable:</br>

1. Classic way:</br>
```
Runnable runnable = new Runnable() {
    @Override
    public void run() {
        System.out.println("Inside Runnable 1");
    }
};
new Thread(runnable).start();
```

2. Lambda way:</br>
```
Runnable runnableLambda = () -> { System.out.println("Inside Runnable 2"); };
```
or</br>
```
Runnable runnableLambdaSimple = () -> System.out.println("Inside Runnable 3");
```
or</br>
```
new Thread(() -> System.out.println("Inside Runnable 4")).start();
```
or</br>
```
Runnable runnableLambdaMultiStatements = () -> { 
    System.out.println("Inside Runnable 5");
    System.out.println("Inside Runnable 6");
};
```
#### Comparator:</br>

1. Classic way:</br>
```
Comparator<Integer> comparator  = new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
};
```

2. Lambda way:</br>
```
Comparator<Integer> comparatorLambda = (Integer  a,Integer b) -> a.compareTo(b);
```
or</br>
```
Comparator<Integer> comparatorLambda1 = (a,b) -> a.compareTo(b);
```
and</br>
```
/**
* Comparator chaining happens only when the first comparators result is zero.
*/
System.out.println("Result of chaining comparator is with equal inputs : " + comparatorLambda1.thenComparing(comparatorLambda1).compare(2,2));
```

3. Functional Interfaces:</br>
```
@FunctionalInterface // optional annotation
```
`Is an interface that has exactly one abstract method.`

3.1 Consumer Functional Interfaces:</br>

```
    // Example 1
    Consumer<String>  upperCase = s -> System.out.println(s.toUpperCase());
    upperCase.accept( "jardel" );
    // JARDEL
    System.out.println("--------------------");

    // Example 2
    List<String> list = Arrays.asList( "Anubis", "Atenas", "Mars", "Loki" );
    list.forEach( upperCase );
    /**
        ANUBIS
        ATENAS
        MARS
        LOKI
        ANUBIS
        */
    System.out.println("--------------------");

    // Example 3
    Consumer<String> appendCase = s -> System.out.println("Random person:" + s);
    list.forEach( upperCase.andThen( appendCase ) );
    /** 
        ANUBIS
        Random person:Anubis
        ATENAS
        Random person:Atenas
        MARS
        Random person:Mars
        LOKI
        Random person:Loki
    */
    System.out.println("--------------------");

    // Example 4
    list.forEach((s) -> {
        if( s.startsWith("A") )
        {
            upperCase.andThen(appendCase).accept(s);
        }
    });
    /**
        ANUBIS
        Random person:Anubis
        ATENAS
        Random person:Atenas
    */
    System.out.println("--------------------");

```
