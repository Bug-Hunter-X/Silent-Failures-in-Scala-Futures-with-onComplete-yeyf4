# Silent Failures in Scala Futures
This example demonstrates a common issue in Scala when using `onComplete` with `Future`s: exceptions inside the `Future` are not automatically propagated to the main thread. The `onComplete` block only handles the `Future`'s completion, but doesn't provide a direct mechanism to handle failures and continue program execution.

## Problem
The provided `bug.scala` file shows a `Future` that throws an exception when the input is 0.  The `onComplete` method handles both success and failure cases, printing messages to the console. However, if an exception occurs, there's no mechanism to catch the exception outside the Future and do something with it, potentially allowing the program to continue running.

## Solution
The `bugSolution.scala` file offers a solution using `recover` to handle exceptions directly within the `Future` before the result is passed to `onComplete`. This ensures that exceptions are handled and processed even though `onComplete` runs after the result.  This is a more robust approach to handling exceptions than using `onComplete` alone.  Alternatively, using `recoverWith` allows for recovering by returning another Future.