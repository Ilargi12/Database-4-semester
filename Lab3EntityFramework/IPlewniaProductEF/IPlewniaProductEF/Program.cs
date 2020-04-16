using System;
using System.Linq;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;

namespace IPlewniaProductEF
{
    class Program
    {
        static void Main(string[] args)
        {
            ProdContext dbProd = new ProdContext();

            Console.WriteLine("Podaj nazwę firmy:");
            string nameComp1 = Console.ReadLine();
            Company comp1 = new Customer{ CompanyName = nameComp1, Discount = 50 };
            dbProd.Companies.Add(comp1);

            Console.WriteLine("Podaj nazwę firmy:");
            string nameComp2 = Console.ReadLine();
            Company comp2 = new Customer { CompanyName = nameComp2, Discount = 0 };
            dbProd.Companies.Add(comp2);

            Console.WriteLine("Podaj nazwę firmy:");
            string nameComp3 = Console.ReadLine();
            Company comp3 = new Supplier { CompanyName = nameComp3, BankAccountNumber = 1234567};
            dbProd.Companies.Add(comp3);

            Console.WriteLine("Podaj nazwę firmy:");
            string nameComp4 = Console.ReadLine();
            Company comp4 = new Supplier { CompanyName = nameComp4, BankAccountNumber = 7654321 };
            dbProd.Companies.Add(comp4);

            dbProd.SaveChanges();

            var query = dbProd.Companies.Include(p => p).ToList();

            Console.WriteLine("FIRMY: ");
            foreach (var comp in query)
            {
                Console.WriteLine("Nazwa firmy: " + comp.CompanyName);
            }
        }
    }
}
