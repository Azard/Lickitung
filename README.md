Lickitung
==========

![lickitung](./lickitung.png)

Lickitung is fork from Scapegoat, enhance the static analyze tool.

Scapegoat is a Scala static code analyzer, what is more colloquially known as a code lint tool or linter. Scapegoat works in a similar vein to Java's [FindBugs](http://findbugs.sourceforge.net/) or [checkstyle](http://checkstyle.sourceforge.net/), or Scala's [Scalastyle](https://github.com/scalastyle/scalastyle).

A static code analyzer is a tool that flag suspicious language usage in code. This can include behavior likely to lead or bugs, non idiomatic usage of a language, or just code that doesn't conform to specified style guidelines.

### Usage

Scala: 2.11.7
sbt: 0.13.9

Lickitung is fork from Scapegoat 0.94.0.

Lickitung is developed as a scala compiler plugin, which can then be used inside your build tool.

See [scalac-scapegoat-plugin](https://github.com/sksamuel/scalac-scapegoat-plugin) for Scapegoat usage
See [sbt-scapegoat](https://github.com/sksamuel/sbt-scapegoat) for SBT integration.


### Reports

Here is sample output from the console during the build for a project with warnings/errors:

```
[warning] [scapegoat] Unused method parameter - org.ensime.util.ClassIterator.scala:46
[warning] [scapegoat] Unused method parameter - org.ensime.util.ClassIterator.scala:137
[warning] [scapegoat] Use of var - org.ensime.util.ClassIterator.scala:22
[warning] [scapegoat] Use of var - org.ensime.util.ClassIterator.scala:157
[   info] [scapegoat]: Inspecting compilation unit [FileUtil.scala]
[warning] [scapegoat] Empty if statement - org.ensime.util.FileUtil.scala:157
[warning] [scapegoat] Expression as statement - org.ensime.util.FileUtil.scala:180

```

And if you prefer a prettier report, here is a screen shot of the type of HTML report scapegoat generates:

![screenshot](https://raw.githubusercontent.com/sksamuel/scapegoat/master/screenshot1.png)

### Configuration

For instructions on suppressing warnings by file, by inspection or by line see [the sbt-scapegoat README](https://github.com/sksamuel/sbt-scapegoat).

### Inspections

There are 107 inspections from original Scapegoat. An overview list is given.

|Name|Brief Description|
|----|-----------|
| ArrayEquals | Checks for comparison of arrays using `==` which will always return false |
| ArraysInFormat| Checks for arrays passed to String.format |
| ArraysToString| Checks for explicit toString calls on arrays |
| AvoidOperatorOverload | Checks for mental symbolic method names | 
| AvoidSizeEqualsZero | Traversable.size can be slow for some data structure, prefer .isEmpty |
| AvoidSizeNotEqualsZero | Traversable.size can be slow for some data structure, prefer .nonEmpty |
| AvoidToMinusOne | Checks for loops that use `x to n-1` instead of `x until n` |
| AsInstanceOf| Checks for use of `asInstanceOf` |
| BigDecimalDoubleConstructor| Checks for use of `BigDecimal(double)` which can be unsafe |
| BoundedByFinalType | Looks for types with upper bounds of a final type |
| BrokenOddness| checks for a % 2 == 1 for oddness because this fails on negative numbers |
| CatchNpe| Checks for try blocks that catch null pointer exceptions |
| CatchThrowable | Checks for try blocks that catch Throwable |
| ClassNames | Ensures class names adhere to the style guidelines |
| CollectionNamingConfusion| Checks for variables that are confusingly named |
| CollectionNegativeIndex| Checks for negative access on a sequence eg `list.get(-1)` |
| CollectionPromotionToAny| Checks for collection operations that promote the collection to `Any` |
| ComparingFloatingPointTypes| Checks for equality checks on floating point types |
| ComparingUnrelatedTypes| Checks for equality comparisons that cannot succeed |
| ComparisonToEmptyList | Checks for code like `a == List()` or `a == Nil` |
| ComparisonToEmptySet | Checks for code like `a == Set()` or `a == Set.empty` |
| ComparisonWithSelf| Checks for equality checks with itself |
| ConstantIf| Checks for code where the if condition compiles to a constant |
| DivideByOne| Checks for divide by one, which always returns the original value |
| DoubleNegation | Checks for code like `!(!b)` |
| DuplicateImport | Checks for import statements that import the same selector |
| DuplicateMapKey| Checks for duplicate key names in Map literals |
| DuplicateSetValue | Checks for duplicate values in set literals |
| EitherGet| Checks for use of .get on Left or Right |
| EmptyCaseClass | Checks for case classes like `case class Faceman()` |
| EmptyCatchBlock| Checks for swallowing exceptions |
| EmptyFor | Checks for empty `for` loops |
| EmptyIfBlock| Checks for empty `if` blocks |
| EmptyInterpolatedString| Looks for interpolated strings that have no arguments |
| EmptyMethod| Looks for empty methods |
| EmptySynchronizedBlock| Looks for empty synchronized blocks |
| EmptyTryBlock| Looks for empty try blocks |
| EmptyWhileBlock | Looks for empty while loops |
| ExistsSimplifableToContains | `exists(x => x == b)` replaceable with `contains(b)` |
| FilterDotHead| `.filter(x => ).head` can be replaced with `find(x => ) match { .. } ` |
| FilterDotHeadOption| `.filter(x =>).headOption` can be replaced with `find(x => )` |
| FilterDotIsEmpty| `.filter(x => Bool).isEmpty` can be replaced with `!exists(x => Bool)` |
| FilterOptionAndGet| `.filter(_.isDefined).map(_.get)` can be replaced with `flatten` |
| FilterDotSize| `.filter(x => Bool).size` can be replaced more concisely with with `count(x => Bool)` |
| FinalizerWithoutSuper | Checks for overriden finalizers that do not call super |
| FindDotIsDefined| `find(x => Bool).isDefined` can be replaced with `exist(x => Bool)` |
| IllegalFormatString| Looks for invalid format strings |
| IncorrectlyNamedExceptions| Checks for exceptions that are not called *Exception and vice versa |
| IncorrectNumberOfArgsToFormat| Checks for wrong number of arguments to `String.format` |
| InvalidRegex| Checks for invalid regex literals |
| ImpossibleOptionSizeCondition | Checks for code like `option.size > 2` which can never be true |
| IsInstanceOf| Checks for use of `isInstanceOf` |
| JavaConversionsUse| Checks for use of implicit Java conversions |
| ListAppend | Checks for List :+ which is O(n) |
| ListSize| Checks for `List.size` which is O(n). |
| LooksLikeInterpolatedString | Finds strings that look like they should be interpolated but are not |
| LonelySealedTrait | Checks for sealed traits which have no implementation |
| MaxParameters | Checks for methods that have over 10 parameters |
| MethodNames | Warns on method names that don't adhere to the Scala style guidelines |
| MethodReturningAny | Checks for defs that are defined or inferred to return `Any` |
| ModOne| Checks for `x % 1` which will always return `0` |
| NanComparison| Checks for `x == Double.NaN` which will always fail |
| NegationIsEmpty | `!Traversable.isEmpty` can be replaced with `Traversable.nonEmpty` |
| NegationNonEmpty | `!Traversable.nonEmpty` can be replaced with `Traversable.isEmpty` |
| NullUse| Checks for use of `null` |
| ObjectNames | Ensures object names adhere to the Scala style guidelines |
| OptionGet| Checks for `Option.get` |
| OptionSize| Checks for `Option.size` |
| ParameterlessMethodReturnsUnit| Checks for `def foo : Unit` |
| PartialFunctionInsteadOfMatch | Warns when you could use a partial function directly instead of a match block |
| PointlessTypeBounds | Finds type bounds of the form `[A <: Any]` or `[A >: Nothing]`
| PreferSeqEmpty| Checks for Seq() when could use Seq.empty |
| PreferSetEmpty| Checks for Set() when could use Set.empty |
| PreferVectorEmpty| Checks for Vector() when could use Vector.empty |
| ProductWithSerializableInferred| Checks for vals that have `Product with Serializable` as their inferred type |
| PublicFinalizer | Checks for overriden finalizes that are public |
| RedundantFinalizer| Checks for empty finalizers. |
| RepeatedCaseBody | Checks for case statements which have the same body |
| SimplifyBooleanExpression | `b == false` can be simplified to `!b` |
| StripMarginOnRegex | Checks for .stripMargin on regex strings that contain a pipe |
| SubstringZero | Checks for `String.substring(0)` |
| SuspiciousMatchOnClassObject | Finds code where matching is taking place on class literals |
| SwallowedException | Finds catch blocks that don't handle caught exceptions | 
| SwapSortFilter| `sort.filter` can be replaced with `filter.sort` for performance |
| TraversableHead| Looks for unsafe usage of `Traversable.head` |
| TryGet| Checks for use of `Try.get` |
| TypeShadowing | Checks for shadowed type parameters in methods |
| UnnecessaryIf| Checks for code like `if (expr) true else false` |
| UnneccessryOverride | Checks for code that overrides parent method but simply calls super |
| UnnecessaryReturnUse| Checks for use of `return` keyword in blocks |
| UnnecessaryToInt | Checks for unnecessary `toInt` on instances of Int |
| UnnecessaryToString | Checks for unnecessary `toString` on instances of String |
| UnreachableCatch | Checks for catch clauses that cannot be reached |
| UnsafeContains| Checks for `List.contains(value)` for invalid types |
| UnusedMethodParameter| Checks for unused method parameters |
| UseCbrt| Checks for use of `math.pow` for calculating `math.cbrt` |
| UseExpM1| Checks for use of `math.exp(x) - 1` instead of `math.expm1(x)` |
| UseLog10| Checks for use of `math.log(x)/math.log(10)` instead of `math.log10(x)` |
| UseLog1P| Checks for use of `math.log(x + 1)` instead of `math.log1p(x)` |
| UseSqrt| Checks for use of `math.pow` for calculating `math.sqrt` |
| VarClosure | Finds closures that reference var |
| VarCouldBeVal | Checks for `var`s that could be declared as `val`s |
| VariableShadowing | Warns for variables that shadow variables or parameters in an outer scope with the same name |
| VarUse| Checks for use of `var` |
| WhileTrue| Checks for code that uses a `while(true)` or `do { } while(true)` block. |
| WildcardImport | Checks for wildcard imports |
| ZeroNumerator | Checks for dividing by 0 by a number, eg `0 / x` which will always return `0` |
