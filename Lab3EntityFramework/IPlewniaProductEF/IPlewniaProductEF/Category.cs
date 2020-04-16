using System;
using System.Collections.Generic;
using System.Text;
using System.Collections.Generic;
using System.Collections.ObjectModel;

namespace IPlewniaProductEF
{
    class Category
    {
        public Category()
        {
            Products = new Collection<Product>();
        }
        public int CategoryID { get; set; }
        public string Name { get; set; }
        public ICollection<Product> Products { get; set; }
    }
}
