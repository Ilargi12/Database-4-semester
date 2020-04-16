using System;
using System.Collections.Generic;
using System.Text;
using System.ComponentModel.DataAnnotations.Schema;
using System.Collections.ObjectModel;

namespace IPlewniaProductEF
{
    class Product
    {
        public Product()
        {
            MediatorProducts = new Collection<MediatorProduct>();
        }
        public int ProductID { get; set; }
        public string Name { get; set; }
        public int UnitsInStock { get; set; }
        public Supplier Supplier { get; set; }
        public Category Category { get; set; }

        public ICollection<MediatorProduct> MediatorProducts { get; set; }
    }
}
