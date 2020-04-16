using System;
using System.Collections.Generic;
using System.Text;
using System.Collections.ObjectModel;

namespace IPlewniaProductEF
{
    class Invoice
    {
        public Invoice()
        {
            MediatorProducts = new Collection<MediatorProduct>();
        }

        public int InvoiceID { get; set; }
        public int InvoiceNumber { get; set; }

        public ICollection<MediatorProduct> MediatorProducts { get; set; }

    }
}
