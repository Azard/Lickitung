Lickitung
==========

[![Build Status](https://travis-ci.org/Azard/Lickitung.svg?branch=master)](https://travis-ci.org/Azard/Lickitung)

![lickitung](./lickitung.png)

Lickitung is fork from Scapegoat, enhance the static analyze tool.

Scapegoat is a Scala static code analyzer, what is more colloquially known as a code lint tool or linter. Scapegoat works in a similar vein to Java's [FindBugs](http://findbugs.sourceforge.net/) or [checkstyle](http://checkstyle.sourceforge.net/), or Scala's [Scalastyle](https://github.com/scalastyle/scalastyle).

A static code analyzer is a tool that flag suspicious language usage in code. This can include behavior likely to lead or bugs, non idiomatic usage of a language, or just code that doesn't conform to specified style guidelines.

### Usage

* Scala: 2.11.7
* sbt: 0.13.9

Lickitung is fork from Scapegoat 0.94.0.

Lickitung is developed as a scala compiler plugin, which can then be used inside your build tool.

* See [scalac-scapegoat-plugin](https://github.com/sksamuel/scalac-scapegoat-plugin) for Scapegoat usage.
* See [sbt-scapegoat](https://github.com/sksamuel/sbt-scapegoat) for SBT integration.


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

![screenshot](./screenshot1.png)

### Configuration

For instructions on suppressing warnings by file, by inspection or by line see [the sbt-scapegoat README](https://github.com/sksamuel/sbt-scapegoat).

### Lickitung Inspections

There are 1 inspections created or enhanced by Lickitung.

|Name|Brief Description|
|----|-----------|
| UnnecessaryOverride | Checks for code that overrides parent method but simply calls super |

### Original Inspections

There are 107 inspections from original Scapegoat 0.94.0. An overview list see [scalac-scapegoat-plugin](https://github.com/sksamuel/scalac-scapegoat-plugin).