# Simple Monte Carlo Integration in Scala

The simplest, really. Probably not very Scala-ish, but it's a start.

Given a continuous function in one variable and lower and upper bounds, calculate
the area under the curve by Monte Carlo integration.

All of this is hard coded, because it's an exercise in numerical methods
rather than non-standard evaluation or processing user input.

## Monte Carlo Integration, or How To Get The Right Answer By Guessing

... kind of.

Start with the defintion of the (arithmetic) mean of a function over a given interval:

![\bar{f(x)}=\frac{1}{N}\sum_{i=1}^N{f(x_{i})};a\leq{x}\leq{b}](https://render.githubusercontent.com/render/math?math=%5Cbar%7Bf(x)%7D%3D%5Cfrac%7B1%7D%7BN%7D%5Csum_%7Bi%3D1%7D%5EN%7Bf(x_%7Bi%7D)%7D%3Ba%5Cleq%7Bx%7D%5Cleq%7Bb%7D)

Now define the same thing in the continuous case rather than the discrete:

![\bar{f(x)}=\frac{1}{b - a}\int\limits_{a}^b{f(x)}dx](https://render.githubusercontent.com/render/math?math=%5Cbar%7Bf(x)%7D%3D%5Cfrac%7B1%7D%7Bb%20-%20a%7D%5Cint%5Climits_%7Ba%7D%5Eb%7Bf(x)%7Ddx)

Now, we can rearrange this expression to get a definition of the integral in terms of the mean and the width of the interval:

![\int_{a}^b{f(x)}dx=(b - a) * \bar{f(x)}](https://render.githubusercontent.com/render/math?math=%5Cint_%7Ba%7D%5Eb%7Bf(x)%7Ddx%3D(b%20-%20a)%20*%20%5Cbar%7Bf(x)%7D)

So, this implies that we can calculate the integral of
![f(x)](https://render.githubusercontent.com/render/math?math=f(x))
over the interval
![f(x)](https://render.githubusercontent.com/render/math?math=[a,b])
by first getting the average of the function over the interval
![f(x)](https://render.githubusercontent.com/render/math?math=f(x))
, and then multiplying by
![f(x)](https://render.githubusercontent.com/render/math?math=[a,b])
.

This is great, because very often we find ourselves in the situation
where evaluating
![f(x)](https://render.githubusercontent.com/render/math?math=f(x))
is easy for any given
![f(x)](https://render.githubusercontent.com/render/math?math=x)
, but _integrating_
![f(x)](https://render.githubusercontent.com/render/math?math=f(x))
over any given integral is very difficult indeed.

The simplest, most naive, approach is to Uniformly sample from the interval ![(b - a)](https://render.githubusercontent.com/render/math?math=(b%20-%20a)), and then evaluate ![f(x)](https://render.githubusercontent.com/render/math?math=f(x)) for each 
![x \sim Unif(a,b)](https://render.githubusercontent.com/render/math?math=x%20%5Csim%20Unif(a%2Cb)).

Now we have a sequence

<img src="https://render.githubusercontent.com/render/math?math=f(x_{1}), f(x_{2}), ..., f(x_{N});&space;x\sim&space;Unif(a,b)">

We can take the mean of this sequence,
multiply by the width of the interval,
and we get our discrete approximation of the integral:

![\int_{a}^{b} f(x) dx\approx(b - a) * \bar{f(x)};\  x\sim Unif(a,b)](https://render.githubusercontent.com/render/math?math=%5Cint_%7Ba%7D%5E%7Bb%7D%20f(x)%20dx%5Capprox(b%20-%20a)%20*%20%5Cbar%7Bf(x)%7D%3B%5C%20%20x%5Csim%20Unif(a%2Cb))

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