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

#### Runnable</br>

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
#### Comparator</br>

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

## 3. Functional Interfaces</br>
```
@FunctionalInterface // optional annotation
```
`Is an interface that has exactly one abstract method.`

### 3.1 Consumer Functional Interface</br>
`Only consumes one input and perform an operation, does not return anything.`

```
    // Example 1
    Consumer<String>  upperCase = s -> System.out.println(s.toUpperCase());
    Consumer<String> appendCase = s -> System.out.println("Random person:" + s);

    upperCase.accept( "jardel" );
    // JARDEL
    System.out.println("--------------------");

    // Example 2
    List<String> list = Arrays.asList( "Anubis", "Atenas", "Mars", "Loki" );
    list.forEach( upperCase );
    list.forEach( each -> upperCase.accept(each) );
    /**
        ANUBIS
        ATENAS
        MARS
        LOKI
        ANUBIS
        */
    System.out.println("--------------------");

    // Example 3
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

### 3.2 BiConsumer Functional Interface</br>
`Consumes two inputs and perform an operation, does not return anything.`

```
/** Example 1 */

BiConsumer<String, String> biConsumer = (a, b) -> { System.out.println(" a : " + a + " b : " + b); };
biConsumer.accept("java7", "java8");
/**
  *  a : java7 b : java8
 */

/** Example 2 */

BiConsumer<Integer, Integer> multiply = (a, b) -> { System.out.println("Multiplication : " + (a * b)); };
BiConsumer<Integer, Integer> addition = (a, b) -> { System.out.println("Addition : " + (a + b)); };
BiConsumer<Integer, Integer> division = (a, b) -> { System.out.println("Division : " + (a / b)); };
multiply.andThen(addition).andThen(division).accept(10, 5);
/**
  *  Addition : 15
  *  Division : 2
 */

/** Example 3 */
BiConsumer<String, List<String>> biConsumer = (name, activities) -> System.out.println(name + " : " + activities);
biConsumer.accept("Roman", Arrays.asList("Mars", "Jupter", "Neptuno") );
```

### 3.3 Predicate Functional Interface</br>
`Receives an input, perform an operation and return a boolean`

```
/** Example 1 */

Predicate<Integer> p1 = (i) -> i % 2 == 0;
Predicate<Integer> p2 = (i) -> i % 5 == 0;

System.out.println("Test : " + p1.test(3));
System.out.println("And : " + p1.and(p2).test(11));
System.out.println("Or : " + p1.or(p2).test(25));
System.out.println("Negate : " + p1.and(p2).negate().test(4));
/**
    * Test : false
    * And : false
    * Or : true
    * Negate : true
*/

/** Example 2 */
Predicate<Double> approved = (s) -> s >= 7;
List<Double> gradeList = Arrays.asList( 2d, 4.7, 9.3, 7.1, 8d, 8.5, 7.8 );

gradeList.stream().filter(approved).collect(Collectors.toList());

/** Example 3 */
Function<Integer, Predicate<Integer>> gradePredicate = (toCompare) -> (number) -> number > toCompare;
boolean result = gradePredicate.apply(7).test(5);

/** Example 4 */
Function<Double, Predicate<Double>> gradePredicate = (toCompare) -> (number) -> number >= toCompare;
Predicate<Double> approved = (s) -> gradePredicate.apply(7d).test(s);

gradeList.stream().filter(approved).collect(Collectors.toList());
```

### 3.4 Combining Predicate + Consumer</br>

```
public class Run {
    static class Deities {
        public String name;
        public double power;

        public Deities( String name, double power ) {
            this.name = name; this.power = power;
        }
    }

    public static void main(String[] args) {
        List<Deities> deities = Arrays.asList( new Deities( "Odin", 10), new Deities( "Zeus", 10), new Deities( "Anubis", 10), new Deities( "Loki", 5) );
        
        Predicate<Deities> isPowerfull = ( god ) -> god.power >= 9;
        BiConsumer<String, Double> godBiConsumer = (name, power) -> System.out.println(name + " : " + power);
        
        Consumer<Deities> consumer = ( god ) -> {

            if(isPowerfull.test(god)){
                godBiConsumer.accept(god.name, god.power);
            }
        };

        deities.forEach( consumer );
    }
}
```

### 3.5 BiPredicate</br>

```
static class Deities {
    public String name;
    public double power;

    public Deities(String name, double power) {
        this.name = name;
        this.power = power;
    }
}

public static void main(String[] args) {
    List<Deities> deities = Arrays.asList(new Deities("Odin", 10), new Deities("Zeus", 10),
            new Deities("Anubis", 10), new Deities("Loki", 5));

    BiPredicate<String, Double> biPredicate = (name, power) -> name.startsWith("O") && power >= 9;
    Consumer<Deities> consumer = (deitie) -> {
        if (biPredicate.test(deitie.name, deitie.power)) {
            System.out.println(deitie.name);
        }
    };

    deities.forEach(consumer);
}
```

### 3.6 Function</br>
`Function<T,R> function =  (input) -> { //do stuff; return something; }`</br>
Expects an input, perform an operation and returns a result.</br>
T - Input  type</br>
R - Output type</br>

```
Function<String,String> upperCase =  (name) -> name.toUpperCase();
System.out.println(upperCase.apply("odin"));
```

### 3.7 BiFunction</br>
`Function<T, U, R> function =  (input1, input2) -> { //do stuff; return something; }`</br>
Expects an input, perform an operation and returns a result.</br>
T - Input  type1</br>
U - Input  type2</br>
R - Output type</br>

### 3.8 UnaryOpeartor</br>
`UnaryOperator<T> unaryOperation = (input) -> return input; `</br>
Expects one input returns a result of the same type after processing.</br>

```
UnaryOperator<String> unaryOperator = (s)->s.concat(": Default");
System.out.println(unaryOperator.apply("java8"));
```

### 3.9 BinaryOpeartor</br>
`BinaryOpeartor<T> binaryOpeartor = (input1, input2) -> return input1+input2; `</br>
Expects two input of the same type and returns a result also of the same type after processing.</br>

```
BinaryOperator<Integer> binaryOperator = (a,b) -> a * b;
System.out.println(binaryOperator.apply(3,4));
```
### 3.10 Supplier</br>
`Supplier<T> supplier = () -> return item;`</br>
Produces an item of type T, lika a database query or get operation.</br>

```
Supplier<List<Deities>> deitiesSupplier = () -> {
    return Arrays.asList(new Deities("Odin", 10), new Deities("Zeus", 10), new Deities("Anubis", 10), new Deities("Loki", 5));
};
System.out.println(deitiesSupplier.get());
```

### 3.11 Variables</br>

```
// Works
private int counter = 0;

public void run(){
    Function<String, String> upperCase = (s) -> { counter++; return s.toUpperCase(); };
    upperCase.apply("test");
    System.out.println(counter);
}

// Works, but must be final
public void run(){
    final int counter = 0;
    
    Function<String, String> upperCase = (s) -> { System.out.println(counter); return s.toUpperCase(); };
    upperCase.apply("test");
    System.out.println(counter);
}
```


## 4. Method Reference
`ClassName::instance-methodName`</br>
`ClassName::static-methodName`</br>
`Instance::methodName`</br>

* Simplify the implementation of Functional interfaces.</br>
* Shortcut for writing Lambda Expressions.</br>
* Refer a method in a class.</br>

```
/**
* Class::instancemethod
*/

// Using Lambda
Function<String, String> function = (s) -> s.toUpperCase();

// Using Method Reference
Function<String, String> method = String::toUpperCase;

/**
* Class::instancemethod
*/

// Using Lambda
Consumer<Student> c2 = (student -> student.printListOfActivities());

// Using Method Reference
Consumer<Student> c3 = (Student::printListOfActivities);

/**
* Predicates
*/

// Using Lambda
Predicate<Student> predicateUsingLambda = (s) -> s.getGradeLevel()>=3;

// Using Method Reference
Predicate<Student> predicateUsingMetRef = RefactorMethodReferenceExample::greaterThan;

/**
* Constructor Reference
*/
Supplier<Deities> supplier = Deities::new; // new Deities()
Function<String, Deities> function = Deities::new; // new Deities(name)
BiFunction<String, Double, Deities> biFunction = Deities::new; // new Deities(name, power)
```

## 5. Stream API
`Stream is a pipeline of aggregate operations that can be applied to process a sequence of elements, can be created out of a collection such as a List or Arrays or any kind of I/O resource.`

```
List<String> list = Arrays.asList( "Odin", "Thor", "Loki" );
list.stream(); // sequential
list.parallelStream(); // parallel
```
* Cannot add or modify data, it is a fixed data set.</br>
* Elements can only be accessed in sequence.</br>
* It's lazily constructed.</br>
* Stream operations can be performed either sequentially or in parallel.</br>
* Can be used with arrays or any kind of I/O.</br>
* Main purpose is to perform some operation on collections.</br>

Streams Parts:

Data Source: provides the elements to the pipeline.

* Example: `List, Array, Collection`

Intermediate Operations: get elements one-by-one and process them.</br>

All intermediate operations are lazy, and, as a result, no operations will have any effect until the pipeline starts to work

* `filter`: filter the elements with Predicate

* `limit(n)`: limit the n of elements to be processed.

* `skip(n)`: skips the n first elements.

* `peek`: Mainly to support debugging, where you want to see the elements as they flow past a certain point in a pipeline. Also usefull when we want to alter the inner state of an element, like converting all name to lowercase before printing them.</br>
```
Stream<User> userStream = Stream.of(new User("Alice"), new User("Bob"), new User("Chuck"));
userStream.peek(u -> u.setName(u.getName().toLowerCase())).forEach(System.out::println);
```

* `map`: converts an object to something else.</br>
```
Set<String> namesUpperCase = students.stream() //Stream<Student>
        .map(Student::getName) // Stream<String>
        .map(String::toUpperCase) // Stream<String> -> UpperCase
        .collect(toList()); // terminal operation
```

* `flatMap`: same as map, but used in the stream context where each element represents multiple elements.</br>
```
List<Deities> deitiesList = pantheonList.stream() // Stream<Deities>
        .map(Pantheon::getDeities) // <Stream<List<Deities>>
        .flatMap(List::stream) // <Stream<String>
        .collect(Collectors.toList()); // collects it to a List.
```

* `filter`: filters the elements in the stream, it's input is a `Predicate Functional`.

* `distinct`: returns a stream with unique elements

* `count`: returns a long with the total number of elements.

* `sorted`: sort the elements in the stream.
```
List<Deities> orderedByName = list.stream() // Stream<Pantheon>
        .peek((pantheon -> {
            System.out.println("peeking:" + pantheon); // peek at pantheon object
        }))
        .map(Pantheon::getDeities) // <List<Deities>
        .flatMap(List::stream) // Stream<Deities>
        .sorted(Comparator.comparing(Deities::getName)) // sorting
        .collect(Collectors.toList()); // collects it to a list.
```

Terminal Operations:

End of the stream lifecycle. Most importantly for our scenario, they initiate the work in the pipeline.

* `anyMatch, allMatch, noneMatch`: takes a Predicate as an input and returns a boolean as output.

* `findFirst, findAny`: return an output as Optional

* `collect`: allows us to perform mutable fold operations (repackaging elements to some data structures and applying some additional logic, concatenating them, etc.) on the elements.</br>
```
.collect(toList());
.collect(toUnmodifiableList());
.collect(toSet());
.collect(toUnmodifiableSet());
.collect(toCollection(LinkedList::new))
.collect(toMap(Function.identity(), String::length))
.collect(toUnmodifiableMap(Function.identity(), String::length))
.collect(collectingAndThen(toList(), ImmutableList::copyOf)) // collect Stream elements to a List instance, and then convert the result into an ImmutableList instance:
.collect(joining("delimiter-", "start-prefix", "end-suffix"));
.collect(counting()); // returns a long
.collect(summarizingDouble(String::length)); // SummarizingDouble/Long/Int 
.collect(summingDouble(String::length)); // returns a sum of extracted elements
.collect(averagingDouble(String::length)); // returns an average of extracted elements
.collect(maxBy(Comparator.naturalOrder())); 
.collect(minBy(Comparator.reverseOrder())); 
.collect(groupingBy(String::length, toSet())); // groups string by their length
.collect(partitioningBy(gpaPredicate)); // returns a list Map<Boolean,List<Student>> 
```

* `reduce`: reduce  the contents of a stream to a single value, takes two inputs: </br>
1. default or initial value (optional, used in the first interaction also).</br>
2. BinaryOperator<T></br>
```
// Example 1
Optional<Integer> reduceValue = integerList.stream()
        .reduce((a, b) -> a * b); // performs multiplication for each element in the stream

// Example 2
String allNamesReduce = list.stream()
        .map(Pantheon::getDeities)
        .flatMap(List::stream)
        .map(Deities::getName)
        .reduce("", ( a, b ) -> a + " -> " + b );
```

### 5.1 Factory Methods

* `of`: creates a stream of the values supplied:</br>
```
Stream<String> stringStream = Stream.of("adam","dan","jenny","dave");
stringStream.forEach(System.out::println);
```

* `iterate`: create an infinete stream which is limited by limit(), inputs are seed and an UnaryOperator:</br>
```
List<Integer> integerList  = Stream.iterate(1, x->x*2)
            .limit(10)
            .map(Integer::new)
            .collect(toList());
System.out.println("iterate : " + integerList);
```

* `generate`: receive an supplier as input, creates new values and is limited by limit():</br>
```
Supplier<Integer> supplier = new Random()::nextInt;

List<Integer> integerList1  = Stream.generate(supplier)
            .limit(10)
            .collect(toList());
System.out.println("generate : " + integerList1);
```

[Java Example]()

### 5.2 Numeric Streams
`IntStream, LongStream, DoubleStream`

* `range`: returns a sequential ordered IntStream from startInclusive to endInclusive by an incremental step of 1, excludes the endInclusive value:</br>

* `rangedClosed`: returns a sequential ordered IntStream from startInclusive to endInclusive by an incremental step of 1:</br>
```
System.out.println(IntStream.rangeClosed(1,6).sum());
```

* `max, min, avg`:</br>
```
OptionalInt max = IntStream.rangeClosed(1,50).max();
OptionalLong min = LongStream.rangeClosed(1,50).min();
OptionalDouble avg = IntStream.rangeClosed(1,50).average();
```

* `boxed`(intermediate operation): returns a Stream consisting of the elements of this stream, each boxed to an Integer:</br>
```
IntStream stream = IntStream.range(3, 8);
Stream<Integer> stream1 = stream.boxed();
stream1.forEach(System.out::println);
```

* `mapToInt, mapToDouble, mapToLong`(intermediate operation): returns an (IntStream, DoubleStream or LongStream) consisting of the results of applying the given function:</br>
```
List<String> list = Arrays.asList("3", "6", "8", "14", "15");
list.stream().mapToInt( num -> Integer.parseInt( num ) ).filter(num -> num % 3 == 0).forEach(System.out::println);
```

* `mapToObj`(intermediate operation): returns an object-valued Stream consisting of the results of applying the given function:</br>
```
IntStream stream = IntStream.range(3, 8);

Stream<String> stream1 = stream.mapToObj( num -> Integer.toBinaryString( num ) );

stream1.forEach(System.out::println);
```

## 6 Parallel Streams

```
System.out.println(Runtime.getRuntime().availableProcessors());

int total = IntStream.rangeClosed(1,1000000)
            .parallel() // splits the data in to multiple parts //775057
            .sum(); //performs the sum of the individual parts and consolidate the result.
```

## 7 Optional

Creating Optional:</br>
```
Optional<String> empty = Optional.empty();
Optional<String> ofNotNull = Optional.of("name"); // can't pass null value, will throw NPE
Optional<String> ofNull = Optional.ofNullable(null); // might pass null value as well
```

Value Presence:</br>
```
System.out.println(ofNotNull.isPresent());
System.out.println(ofNull.isEmpty());
```

Conditional:</br>
```
Optional<String> opt = Optional.of("Jardel");
opt.ifPresent(name -> System.out.println(name.length()));
```

Default Value/Or Else:</br>
```
String nullName = null;
String nameElse = Optional.ofNullable(nullName).orElse("john"); // expects a default value

String nameElseGet = Optional.ofNullable(nullName).orElseGet(() -> "john"); // expects a supplier functional interface
assertEquals("john", name);

String nameException = Optional.ofNullable(nullName).orElseThrow(IllegalArgumentException::new);
```

