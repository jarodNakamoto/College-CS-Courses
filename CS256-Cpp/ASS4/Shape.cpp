#include <iostream>
using namespace std;

class Shape
{
	public:
		double getArea()
		{
			return area;
		}
		double getPerimeter()
		{
			return perimeter;
		}
		virtual void setArea(double a);
		virtual void setPerimeter(double p);
	private:
		double area;
		double perimeter;
};


class Rectangle: public Shape
{
	public:
		double getArea()
		{
		      return width * length;
		}
		double getPerimeter()
		{
			return 2*width + 2*length;
		}
		void setArea(double a)
		{
			area = a;
		}
		void setPerimeter(double p)
		{
			perimeter = p;
		}

	private:
		double width;
		double length;
};

int main()
{
	return 0;
}