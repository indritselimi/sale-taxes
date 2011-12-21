Domain terms:
Product
Product Categories
Basket
Receipt
Sale Tax Rules
Rounding Price Rules

Explanation:
I have choose to create a "tax aware" product. To handle tax calculation I have choose to inject tax rules on my products.
My first idea was to choose which tax rule to inject in "wiring" time (in order to move IF-s into factory...) but it seemed that
a more traditional approach( all taxes are injected, the rule choose when to apply ) was simpler.
For the last reason I have also controlled the tax rule engagement in base of two booleans which it is somewhat ugly;
if needed in the future the concept can be elaborated more on "TaxContext" or similar.

Your feedback is highly appreciated because I can improve my self.

Thank you very much.

