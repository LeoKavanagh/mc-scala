# Simple Monte Carlo Integration in Scala

The simplest, really. Probably not very Scala-ish, but it's a start.

Given a continuous function in one variable and lower and upper bounds, calculate
the area under the curve by Monte Carlo integration.

All of this is hard coded, because it's an exercise in numerical methods
rather than non-standard evaluation or processing user input.

## Monte Carlo Integration, or How To Get The Right Answer By Guessing

... kind of.

Start with the defintion of the (arithmetic) mean of a function over a given interval:

<img src="https://latex.codecogs.com/svg.latex?\Large&space;\bar{f(x)}=\frac{1}{N}\sum\limits_{i=1}^N{f(x_{i})};a\leq{x}\leq{b}" />

Now define the same thing in the continuous case rather than the discrete:

<img src="https://latex.codecogs.com/svg.latex?\Large&space;\bar{f(x)}=\frac{1}{b - a}\int\limits_{a}^b{f(x)}dx" />

Now, we can rearrange this expression to get a definition of the integral in terms of the mean and the width of the interval:

<img src="https://latex.codecogs.com/svg.latex?\Large&space;\int\limits_{a}^b{f(x)}dx=(b - a) * \bar{f(x)}" />

So, this implies that we can calculate the integral of <img src="https://latex.codecogs.com/svg.latex?\Large&space;f(x)"> 
over the interval <img src="https://latex.codecogs.com/svg.latex?\Large&space;[a,b]"> by first getting the average of the function 
over the interval <img src="https://latex.codecogs.com/svg.latex?\Large&space;f(x)">, and then multiplying by <img src="https://latex.codecogs.com/svg.latex?\Large&space;(b-a)">.

This is great, because very often we find ourselves in the situation where evaluating <img src="https://latex.codecogs.com/svg.latex?\Large&space;f(x)"> is easy for any given <img src="https://latex.codecogs.com/svg.latex?\Large&space;x">, but _integrating_ <img src="https://latex.codecogs.com/svg.latex?\Large&space;f(x)"> over any given integral is very difficult indeed.

The simplest, most naive, approach is to Uniformly sample from the interval <img src="https://latex.codecogs.com/svg.latex?\Large&space;f(x)">, and then evaluate <img src="https://latex.codecogs.com/svg.latex?\Large&space;f(x)" /> for each 
<img src="https://latex.codecogs.com/svg.latex?\Large&space;x\sim&space;Unif(a,b)" />.

Now we have a sequence <img src="https://latex.codecogs.com/svg.latex?\Large&space;f(x_{1}), f(x_{2}), ..., f(x_{N});&space;x\sim&space;Unif(a,b)">.

We can take the mean of this sequence,
multiply by the width of the interval,
and we get our discrete approximation of the integral:

<img src="https://latex.codecogs.com/svg.latex?\Large&space;\int\limits_{a}^b{f(x)}dx\approx(b - a) * \bar{f(x)};&space;x\sim&space;Unif(a,b)"/>

## Setup

I used [Darren Wilkinson's](https://github.com/darrenjw/)
Giter8 template to get started. It gives you the [Breeze](https://github.com/scalanlp/breeze)
package and a `main` function.
This is all I need, and is much handier than having to
write a `build.sbt` file myself.

```
sbt new darrenjw/breeze.g8
```

Run with SBT

```
sbt run
```

## Making This Less Useless

Redefine the function `f` inside the `Main` object,
and you're good to go with your own continuous function in one variable.
Extending this to accepting user-inputted functions is left as an
exercise to the reader.
Please do tell me if you achieve this, because I haven't tried yet.

## Making This Less Mathematically Naive

That's a pretty big ask. Ultimately it leads to things like
[Markov Chain Monte Carlo](
https://en.wikipedia.org/wiki/Markov_chain_Monte_Carlo), via
things like [Importance Sampling](https://en.wikipedia.org/wiki/Markov_chain_Monte_Carlo).

Alternatively, there are sophisticated non-stochastic numerical
integration methods like
[Simpson's Rule](https://en.wikipedia.org/wiki/Simpson%27s_rule)
or
[Gaussian Quadrature](https://en.wikipedia.org/wiki/Simpson%27s_rule).