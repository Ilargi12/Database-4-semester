using System;
using System.Collections.Generic;
using System.Text;

namespace IPlewniaProductEF
{
    class MediatorProduct
    {
        public int MediatorProductID { get; set; }
        public int ProductID { get; set; }
        public Product Product { get; set; }
        public int Quantity { get; set; }
        public int InvoiceID { get; set; }
        public Invoice Invoice { get; set; }



    }
}
