using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;
using System.ComponentModel.DataAnnotations.Schema;


namespace IPlewniaProductEF
{
    class Supplier : Company
    {
        public Supplier()
        {
            Products = new Collection<Product>();
        }
        public int BankAccountNumber { get; set; }
        public ICollection<Product> Products { get; set; }
    }
}
